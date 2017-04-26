package outils.interpretor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import linda.*;

public class Shell {

	private InputStream in;
	private Map<String, Process> processes;
	private Linda linda;

	public Shell() {
		this(System.in);
	}

	public Shell(InputStream in) {
		this(in, new shm.CentralizedLinda());
	}

	public Shell(InputStream in, Linda linda) {
		this(in, linda, new HashMap<>());
	}

	public Shell(InputStream in, Linda linda, Map<String, Process> processes) {
		this.in = in;
		this.linda = linda;
		this.processes = processes;
	}

	public void run() {
		boolean exit = false;

		Scanner console = new Scanner(in);


		if (in == System.in) {
			System.out.print("linda> ");
		}
		// <processID> <action> [options...]
		// exit ou create <processID> are special actions
		while (!exit && console.hasNextLine()) {
			String commandLine = console.nextLine();

			if (commandLine.isEmpty()) {
				continue;
			}

			String[] requetes = commandLine.split("\\|\\|");

			for (String requete : requetes) {
				String[] tokens = requete.trim().split("\\s");
				commandLine(tokens);
			}

			if (in == System.in) {
				System.out.print("linda> ");
			}
		}

		console.close();
	}

	private void commandLine(String[] tokens) {
		switch (tokens[0]) {
			case "create":
				for (int iProcess = 1; iProcess < tokens.length; iProcess++) {
					Process process = new Process(tokens[iProcess], linda);
					processes.put(tokens[iProcess], process);
					process.start();
				}
				break;
			case "debug":
				linda.debug("<user>");
				break;
			case "help":
				System.out.println("These are common Linda commands used in various situations:\n"
						+ "create <name>:	create a process with given name\n"
						+ "list: print all processes\n"
						+ "read <file>: execute commands from file within the same environment.\n"
						+ "debug: print the tuple space\n"
						+ "exit:	close the current shell and all associated process\n"
						+ "<process_name> <action>: execute an action on process\n"
						+ "\t write <tuple>:	write the given tuple\n"
						+ "\t take <tuple>:		take a matching tuple\n"
						+ "\t read <tuple>:		read a matching tuple\n"
						+ "\t tryTake <tuple>:	try to take a matching tuple\n"
						+ "\t tryRead <tuple>:	try to read a matching tuple\n"
						+ "\t takeAll <tuple>:	take all matching tuples\n"
						+ "\t eventRegister <read|take> <immediate|future> <tuple>: register event as described\n"
						+ "\t terminate: 		terminate the process\n"
						+ "You can parallelized request with the || operator.\n"
						+ "Lines starting with \"// \" are not processed.");
				break;
			case "list":
				for (String process : processes.keySet()) {
					System.out.println(process + " is " + (processes.get(process).isWaiting() ? "waiting" : "available"));
				}
				break;
			case "read":
				if (tokens.length <= 1) {
					System.out.println("Missing arguments after \"" + tokens[0] + "\"");
					System.out.println("Type \"help\" for more information");
					return;
				}
				try {
					FileInputStream is = new FileInputStream(new File(tokens[1]));
					//InputStream stdin = System.in;
					new Shell(is, linda, processes).run();
				} catch (FileNotFoundException e) {
					System.out.println("File \"" + tokens[1] + "\" does not exist");
				}
				break;
			case "exit":
				for (Thread t : processes.values()) {
					t.interrupt();
				}
				return;
			case "//":
				break;
			default:
				if (!processes.containsKey(tokens[0])) {
					System.out.println("Command \"" + tokens[0] + "\" does not exists");
					System.out.println("Type \"help\" for more information");
					break;
				}
				try {

					performAction(tokens);
				} catch (ActionInvalidException e) {
					System.out.println(e.getMessage());
				}
		}
	}

	private void performAction(String[] action) throws ActionInvalidException {
		Process process = processes.get(action[0]);

		if (action.length <= 1) {
			System.out.println("Missing arguments after \"" + action[0] + "\"");
			System.out.println("Type \"help\" for more information");
			return;
		}

		String[] actionProcess = {action[1], ""};
		for (int iChaine = 2; iChaine < action.length; iChaine++) {
			actionProcess[1] += action[iChaine] + (iChaine != action.length ? " " : "");
		}
		process.setAction(actionProcess);
	}

}
