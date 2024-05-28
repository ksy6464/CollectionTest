package homework;

import com.sun.javafx.geom.Area;

public class Homework04 {
	
	public enum Planet{
		수성(2439), 
		금성(6052), 
		지구(6371), 
		화성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);
		
		private Integer data;

		private Planet(Integer data) {
			this.data = data;
		}

		// getter메서드
		public Integer getData() {
			return data;
		}

	}
	
	

//	public static double surfaceArea(double rad) {
	public static double surfaceArea(double rad) {
		
//		double area = 4*Math.PI*rad*rad;
//		return area;
		return 4*Math.PI*rad*rad;
	}
	
	public static void main(String[] args) {
		Planet mercury = Planet.valueOf("수성");
		Planet venus = Planet.valueOf("금성");
		Planet earth = Planet.valueOf("지구");
		Planet mars = Planet.valueOf("화성");
		Planet jupiter = Planet.valueOf("목성");
		Planet saturn = Planet.valueOf("토성");
		Planet uranus  = Planet.valueOf("천왕성");
		Planet neptune = Planet.valueOf("해왕성");

		System.out.println();
		System.out.println(mercury+"의 반지름 : " +mercury.getData());
		double mercuRad = mercury.getData();
		System.out.println(venus+"의 반지름 : " +venus.getData());
		double venusRad = venus.getData();
		System.out.println(earth+"의 반지름 : " +earth.getData());
		double earthRad = earth.getData();
		System.out.println(mars+"의 반지름 : " +mars.getData());
		double marsRad = mars.getData();
		System.out.println(jupiter+"의 반지름 : " +jupiter.getData());
		double jupiterRad = jupiter.getData();
		System.out.println(saturn+"의 반지름 : " +saturn.getData());
		double saturnRad = saturn.getData();
		System.out.println(uranus+"의 반지름 : " +uranus.getData());
		double uranusRad = uranus.getData();
		System.out.println(neptune+"의 반지름 : " +neptune.getData());
		
		double mercuryArea = surfaceArea(mercury.getData());
		System.out.println("수성의 면적"+mercuryArea);
		
		double venusArea = surfaceArea(venus.getData());
		System.out.println("금성의 면적"+venusArea);
		
		double earthArea = surfaceArea(earth.getData());
		System.out.println("지구의 면적"+earthArea);
		
		double marsArea = surfaceArea(mars.getData());
		System.out.println("화성의 면적"+marsArea);
		
		double jupiterArea = surfaceArea(jupiter.getData());
		System.out.println("목성의 면적"+jupiterArea);
		
		double saturnArea = surfaceArea(saturn.getData());
		System.out.println("토성의 면적"+saturnArea);
		
		double uranusArea = surfaceArea(uranus.getData());
		System.out.println("천왕성의 면적"+uranusArea);
		
		double neptuneArea = surfaceArea(neptune.getData());
		System.out.println("해왕성의 면적"+neptuneArea);
		
	
		
		
	}	
		
}
