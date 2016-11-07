
public class Camion extends Thread {
	
	private Site[] sites;
	private Client[] clients;
	private int currentSite;
	private int stock;
	private int Ubound;
	private int Lbound;
	private boolean running;
	
	public Camion(Site[] Sites, int U, int L, Client[] c){
		clients = c;
		stock = 10;
		sites = Sites;
		currentSite = 0;
		Ubound = U;
		Lbound = L;
		running = true;
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
		//System.out.println("Marcel se déplace du site ["+oldSite +"] au site ["+currentSite+"]");
	}
	
	public void work(){
		int stockSite = sites[currentSite-1].getStock();
		int stockSiteOld = stockSite;
		int stockSiteInit = sites[currentSite-1].getStockInit();
		if(stockSite > Ubound){
			while(sites[currentSite-1].getStock() > stockSiteInit){
				sites[currentSite-1].takeBike();
				stock++;
				stockSite++;
			}
		}else if(stockSite < Lbound){
			while(sites[currentSite-1].getStock() < stockSiteInit && stock > 0){
				sites[currentSite-1].putBike();
				stock--;
				stockSite--;
			}
		}
		//System.out.println("Marcel a transféré [" + (stockSiteOld - stockSite) + "] vélos au site [" + currentSite+"]");
	}
	
	public void run(){
		while(running){
			
			move();
			work();
		}
	}


	public void stopCamion() {
		running = false;
		System.out.println("FIN");
	}
}
