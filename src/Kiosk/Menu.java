package Kiosk;

public class Menu {
	public int menu_num=0;
	public String name=null;
	public int count = 0;
	public int carbo =0;
	public int protein=0;
	public int fat=0;
	public int kcal=0;
	public int price=0;
	
	public Menu() {
		
	}
	public int getMenu_num() {
		return menu_num;
	}

	public void setMenu_num(int menu_num) {
		this.menu_num = menu_num;
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
