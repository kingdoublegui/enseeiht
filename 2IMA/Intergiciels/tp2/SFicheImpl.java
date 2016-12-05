import java.io.*;

public class SFicheImpl implements SFiche {
    private String name;
    private String mail;

    public SFicheImpl (String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

	public String getNom () {
        return name;
    }

	public String getEmail () {
        return mail;
    }
}



