Moniteurs en Java (rappel) :
--------------------

 - Créer un object de type java.util.concurrent.locks.Lock
   (à partir de java.util.concurrent.locks.ReentrantLock) :
   
      `mon_moniteur = new java.util.concurrent.locks.ReentrantLock();`
      
   Cet object réalise le moniteur en fournissant, par son utilisation
   explicite, l'exclusion mutuelle et permet de créer des
   variables-conditions associées au verrou.

 - Créer une ou des variables-conditions (java.util.concurrent.locks.Condition)
   à partir du "lock" précédemment créé :
   
       `ma_var_condition = mon_moniteur.newCondition();`

 - Les procédures doivent explicitement manipuler le verrou pour obtenir
   l'exclusion mutuelle :
   
    `ma_procédure() {`   
    `  mon_moniteur.lock();`     
       
    `     ...           `
    
    `  mon_moniteur.unlock(); `         
    `  }`

 - Les variables-conditions s'utilisent avec
 
       `ma_var_condition.await();`     
   et  
       `ma_var_condition.signal();`      
       `ma_var_condition.signalAll();`
   
 - La sémantique est priorité au signaleur, sans file des signalés :
 
   	* l'attente sur les conditions est FIFO, mais
	* le signaleur conserve l'accès exclusif
	* le signalé est réveillé, mais doit réacquérir l'accès exclusif, et il n'est pas
 	   prioritaire par rapport aux nouveaux entrants.
   Il faut donc penser à utiliser des boucles "while" pour déterminer si les conditions 
   attendues sont effectivement vérifiées au moment du réveil.
