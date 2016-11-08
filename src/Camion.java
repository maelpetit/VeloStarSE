
public class Camion extends Thread {
	
	private Site[] sites;
	private int currentSite;
	private int stock;
	
	public Camion(Site[] Sites){
		stock = 10;
		sites = Sites;
		currentSite = 0;
		this.setDaemon(true);
	}
	
	
	public void move(){
		int oldSite = currentSite;
		if(currentSite == sites.length){
			currentSite = 1;
			try {
				sleep(100*sites.length);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			currentSite++;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("Marcel se d�place du site ["+oldSite +"] au site ["+currentSite+"]");
	}
	
	public void work(){
		sites[currentSite-1].enterCamion(this);
	}
	
	public void run(){
		while(true){
			move();
			work();
		}
	}


	public int getStock() {
		// TODO Auto-generated method stub
		return stock;
	}


	public void addBike() {
		// TODO Auto-generated method stub
		stock++;
	}


	public void rmvBike() {
		// TODO Auto-generated method stub
		stock--;
	}
}
