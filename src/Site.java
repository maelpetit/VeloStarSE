
public class Site {
	
	private int id;
	private int stock;
	private int stockInit;
	private int stockMax;
	
	public Site(int ID, int StockInit, int stockmax){
		id = ID;
		stock = StockInit;
		stockInit = StockInit;
		stockMax = stockmax;
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
		
		notifyAll();
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
		notifyAll();
	}
}
