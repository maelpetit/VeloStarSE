
public class Site {
	
	private int id;
	private int stock;
	private int stockInit;
	private int stockMax;
	private boolean camion;
	private int Ubound;
	private int Lbound;
	
	public Site(int ID, int StockInit, int stockmax, int U, int L){
		id = ID;
		stock = StockInit;
		stockInit = StockInit;
		stockMax = stockmax;
		camion = false;
		Ubound = U;
		Lbound = L;
	}
	

	public boolean isCamion() {
		return camion;
	}


	public void setCamion(boolean camion) {
		this.camion = camion;
	}


	public int getId() {
		return id;
	}

	public int getStock() {
		return stock;
	}

	public int getStockInit() {
		return stockInit;
	}
	
	public int getStockMax(){
		return stockMax;
	}
	
	public synchronized void takeBike() {
		while(stock < 1){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stock--;
		//System.out.println("Il reste ["+stock+"] velos au Site ["+id+"] (-1)");
		notify();
	}

	public synchronized void putBike() {
		while(stock >= stockMax){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stock++;
		//System.out.println("Il reste ["+stock+"] velos au Site ["+id+"] (+1)");
		notify();
	}


	public synchronized void enterCamion(Camion c) {
		
		int stockOld = stock;
		if(stock > Ubound){
			while(stock > stockInit){
				takeBike();
				c.addBike();
			}
		}else if(stock < Lbound){
			while(stock < stockInit && c.getStock() > 0){
				putBike();
				c.rmvBike();
			}
		}
		//System.out.println("Marcel a transfere [" + (stockOld - stock) + "] velos au site [" + id+"]");
		notifyAll();
	}
}
