package src;
public class Move{
	public boolean isCapture;
	public int xi;
	public int yi;
	public int xf;
	public int yf;
	public Move(int xi, int yi, int xf, int yf){
		this.xi = xi;
		this.yi = yi;
		this.xf = xf;
		this.yf = yf;
		if(Math.abs(xi - xf) == 2){
			isCapture = true;
		}else{
			isCapture = false;
		}
	}
	public boolean isCapture(){
		return isCapture;
	}
	public int xf(){
		return xf;
	}
	public int yf(){
		return yf;
	}
	public int xi(){
		return xi;
	}
	public int yi(){
		return yi;
	}
	/*
	public void changeBoard(Board b){
		return;
	}*/
}