package Kiosk;

public class Menu {
	private String name;
	private int count = 0;
	private int carbo;
	private int protein;
	private int fat;
	private int kcal;
	private int price;
	
	public Menu() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCarbo() {
		return carbo;
	}

	public void setCarbo(int car) {
		this.carbo = car;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public void add() {
		this.count++;
	}
	public void sub() {
		this.count--;
	}
	
}
