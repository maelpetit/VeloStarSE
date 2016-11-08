
public class Client extends Thread {
	
	int id;
	Site start;
	Site finish;
	
	public Client(int i){id = i;}
	
	public void setStart(Site start) {
		this.start = start;
	}

	public void setFinish(Site finish) {
		this.finish = finish;
	}

	public void move(){
		start.takeBike();
		//System.out.println("Client ["+id+"] a pris un velo au site ["+start.getId()+"]");
		try {
			sleep(100*Math.abs(start.getId() - finish.getId()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finish.putBike();
		System.out.println("Client ["+id+"] a repose son velo au site : "+finish.getId());
	}
	
	public void run(){
		
		move();
	}
	
}
