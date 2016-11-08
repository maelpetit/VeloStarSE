
public class Programme {
	
	private final static int STOCKINIT = 6;
	private final static int STOCKMAX = 10;
	private final static int UBOUND = 8;
	private final static int LBOUND = 2;
	private final static int NBSITES = 10;
	private final static int NBBIKERS = 300;

	public static void main(String[] args) {
		System.out.println("DEBUT");
		Site[] sites = new Site[NBSITES];
		Client[] clients = new Client[NBBIKERS];
		Camion marcel = new Camion(sites);
		int start;
		int finish;
		
		for(int i = 0; i < NBSITES; i++){
			sites[i] = new Site(i+1, STOCKINIT, STOCKMAX, UBOUND, LBOUND);
		}
		
		for(int i = 0; i < NBBIKERS; i++){
			clients[i] = new Client(i);
			
			start = (int) (Math.random()*NBSITES);
			finish = (int) (Math.random()*NBSITES);
			while(start == finish){
				finish = (int) (Math.random()*NBSITES);
			}
			
			clients[i].setStart(sites[start]);
			clients[i].setFinish(sites[finish]);
			//System.out.println("Client ["+i+"] va du site ["+(start+1)+"] au site ["+(finish+1)+"]");
			clients[i].start();
		}
		
		
		marcel.start();
		
		for(int i = 0; i < NBBIKERS; i++){
			try {
				clients[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("FIN");
		
		for (int i = 0; i < sites.length; i++) {
			System.out.println("Site ["+(i+1)+"] possede : "+sites[i].getStock());			
		}
	}

}
