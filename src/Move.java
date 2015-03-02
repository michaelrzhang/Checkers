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
	public boolean equals(Move other){
		return xi == other.xi() && xf == other.xf() && yi == other.yi() && yf == other.yf();
	}
	public void change(Board b){
		int[][] grid = b.getGrid();
		grid[xf][yf] = grid[xi][yi];
		b.getGrid()[xi][yi] = 0;
		if(isCapture){
			int x = (xf + xi)/2;
			int y = (yf + yi)/2;
			grid[x][y] = 0;
			b.setCapturePiece(xf, yf);
		}
		if(yf == 7 && grid[xf][yf] == 1 || yf == 0 && grid[xf][yf] == -1){
			grid[xf][yf] = 2* b.getTurn();
		}
	}
	public Board changeBoard(Board b){
		Board newb = new Board(b);
		int[][] grid = newb.getGrid();
		grid[xf][yf] = grid[xi][yi];
		grid[xi][yi] = 0;
		if(isCapture){
			int x = (xf + xi)/2;
			int y = (yf + yi)/2;
			grid[x][y] = 0;
			newb.setCapturePiece(xf, yf);
		}
		if(yf == 7 && grid[xf][yf] == 1 || yf == 0 && grid[xf][yf] == -1){
			grid[xf][yf] = 2* b.getTurn();
		}
		return newb;
	}
}