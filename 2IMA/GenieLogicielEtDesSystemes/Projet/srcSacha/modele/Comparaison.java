package modele;

public   class Comparaison {

	private Objet objet;
	private Comparateur comp;
	private Connaissance con;
	private boolean conn;
	
	public Comparaison(){
		this.conn=false;
	}
	public void setObjet(Objet o){
		this.objet=o;
	}
	public void setComparateur(Comparateur c){
		this.comp=c;
	}
	public void setConnaissance(Connaissance c){
		this.conn=true;
		this.con=c;
	}
	public Objet getObjet() {
		return objet;
	}
	public Comparateur getComp() {
		return comp;
	}
	public Connaissance getCon() {
		return con;
	}
	public boolean isConn() {
		return conn;
	}
	
	
}
