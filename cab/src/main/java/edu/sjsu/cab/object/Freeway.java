 package edu.sjsu.cab.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.Scanner;
 
public class Freeway {

	private ArrayList<String> exit; //store exist #s
	private HashMap<String, String> exitDescription;//store description w/ exit number
	private HashMap<String, ArrayList<Double>> exitLatLong;
	private HashMap<String, ArrayList<String>> exitCity= new HashMap<String, ArrayList<String>>();
	
	//------------- CONSTRUCTORS ------------------
	public Freeway(){
		
		this.exit = new ArrayList<String>();//store exist #s
		this.exitDescription = new HashMap<String, String>();//store description w/ exit number
		this.exitLatLong = new HashMap<String, ArrayList<Double>>();
		this.exitCity = new HashMap<String, ArrayList<String>>();
	}
	
	//--------- GETTER SETTERS --------------------
	public void setExit(String e){exit.add(e);}
	public void setArrayOfExits(String[] e){for(int i = 0; i < e.length; i++){this.exit.add(e[i]);}}
	public void setExitDescription(String e, String desc){this.exitDescription.put(e, desc);}
	public void setExitDescriptionArray(String[] e, String[] desc){
		
		if(e.length != desc.length)
			{ System.err.println("ERROR: Exit length (" +e.length+ ") and Exit Description length ("+desc.length+") "
					+ "are mismatched.");
		} else {
			for(int i=0; i < desc.length; i++){
				this.exitDescription.put(e[i],desc[i]);
			}
		}
	}
	public void setExitLatLong(String e, double lat, double lon){ this.exitLatLong.put(e, new ArrayList<Double>(Arrays.asList(lat,lon)));}
	public void setExitLatLongArray(String[] e, ArrayList<List<Double>> latlong){
		
		ArrayList<Double> temp = new ArrayList<Double>();
		
		for(int i=0; i<e.length; i++){
			temp.addAll(latlong.get(i*2));
			temp.addAll(latlong.get(i*2+1));
			this.exitLatLong.put(e[i], temp);
			
		}
	}
	//public void setExitCity(int e, String city){} //<-- Needed?
	public ArrayList<String> getExitList(){ return this.exit;}
	public String getExitDescription(int e){ return this.exitDescription.get(e);}
	public ArrayList<String> getExitDescriptionList(){return (ArrayList<String>)this.exitDescription.values();}
	public ArrayList<Double> getLatLong(int e){return this.exitLatLong.get(e);}
	public Collection<ArrayList<Double>> getLatLongList(){ return this.exitLatLong.values();}
	public HashMap<Double, Double> getExitCity(int e){return null;}
	public HashMap<Double, Double> getCityList(int e){return null;}
	
	
	//-----------PUBLIC METHODS -------------------
	//public sortFreewayByExit(int ascDesc){}	//<-- Needed?
	//public sortFreewayByExitDesc(int ascDesc) //<-- Needed?
	public void printExitList(){System.out.println(this.exit.toString());}
	public void printDescriptionList(){System.out.println(this.exitDescription.values().toString());}
	public void printExitAndDescriptionList(){System.out.println(this.exitDescription.toString());}
	@SuppressWarnings("null")
	public ArrayList<List<Double>> latLongLoader(){//may 
		
		ArrayList<List<Double>> latlong = new ArrayList<List<Double>>();
		int isFinished = 0, i = 0;
		double lat, lon;
        Scanner in = new Scanner(System.in); 

        System.out.println("\nThis method retrieves lat and longs. Enter -1 to stop and any number to continue: ");
		isFinished = in.nextInt();
		
		while(isFinished != -1){
			
			System.out.println("\nEnter latitude #"+i+":");
			lat = in.nextDouble();
			System.out.println("\nEnter longtitude #"+i+":");
			lon = in.nextDouble();
			latlong.add(Arrays.asList(lat, lon));
			System.out.println("Added set "+i+":"+latlong.subList(i, i+1)+")");
			System.out.println("\nThis method retrieves lat and longs. Enter -1 to stop and any number to continue: ");
			isFinished = in.nextInt();  
			i++;
		}
		
		System.out.println(latlong.toString());
		in.close();
		return latlong;
		
	}
	public void printLatLongList(){System.out.println(this.exitLatLong.values().toString());}
	//public void printExitDescriptionLatLong()
	
	public static void main(String[] args) {
		
		//create data structures for 101N/S Freeway
		Freeway fwy101N = new Freeway();
		Freeway fwy101S = new Freeway();		
		String[] fwy101N_Exits ={"353","355","356","357","360","362","365","366","367","371","373","377A","377B","378",
		                        "380","381","382","383","384","385","386A","386B","388A","388B","388C","389A","389B",
		                        "391","392","393","394","395","396A","396B","396C","397","398","399A","399B",
		                        "400A","400B","400C","402","403","404A","404B","406","408","409","411","412","414A",
		                        "414B","415","416","417A","417B","419A","419B","420","422","423A","423B","423C","424",
		                        "425A","425B","426A","426B","429A","429B","429C","430A","430B","431","432","433A","433B",
		                        "433C","434A","434B","438","439"};

		String[] fwy101S_Exits ={"353","355","356","357","360","362","365","366","367","371","373","377A","377B","378",
								"380","382","383","385A","385B","386A","386B","388A","388B","388C","389","390",
				                "391A","391B","392","393","394","395A","395B","396A","396B","397","398A","398B",
				                "399","400A","400B","400C","402","403","404A","404B","406","408","409","411","412","414A",
				                "414B","416","417","419A","419B","420","423A","423B","424","425A","425B","425C","426",
				                "429A","429B","430A","430B","431","432","433","437","438","439"};

		String[] fwy101S_Desc = {"SR-25 East","Monterey Street","SR-152 East / 10th Street / Pacheco Pass Rd",
			                    "SR-152 West / Leavesley Road","Masten Avenue", "San Martin Avenue",
			                    "Tennant Avenue","East Dunne Avenue / Morgan Hill","Cochrane Road", 
			                    "Coyote Creek Golf Drive","Bailey Avenue", "Silicon Valley Blvd / Bernal Road",
			                    "SR-85 North / Cupertino / Mtn View","SR-82 / Blossom Hill Rd / Silver Creek Valley Rd",
			                    "Hellyer Avenue","Capitol Expressway / Yerba Buena Road","Tully Road","Story Road",
			                    "I-280 / I-680 North / Sacramento / SF / Downtown San",
			                    "SR-130 East / Alum Rock Ave / Santa Clara Street","Julian Street / McKee Road",
			                    "Oakland Road / 13th Street","I-880 North / Oakland",
			                    "I-880 South / Santa Cruz / Los Gatos","Brokaw Road / First Street",
			                    "SR-87 / Guadalupe Parkway","Trimble Road", "Santa Clara / De La Cruz Blvd",
			                    "Montague Expressway / San Tomas Expressway","Great America Parkway / Bowers Avenue",
			                    "Lawrence Expressway","Fair Oaks Avenue North","Fair Oaks Avenue South",
			                    "Sunnyvale / Mathilda Avenue","SR-237 East / Alviso / Milpitas",
			                    "Ellis Street / Moffett South Gate","Moffett Blvd / NASA Pkwy",
			                    "SR-85 South / Cupertino / Santa Cruz","Mountain View / Shoreline Blvd",
			                    "Rengstorff Avenue","San Antonio Road North","Los Altos / San Antonio Road South",
			                    "Embarcadero Road / Oregon Expressway","University Avenue","Willow Road East",
			                    "Willow Road West","SR-84 East / Marsh Road / Dumbarton Bridge",
			                    "SR-84 West / Woodside Road / Seaport","Whipple Avenue","Holly Street / Brittan Avenue",
			                    "Ralston Avenue","Hillsdale Blvd / Foster City",
			                    "SR-92 West / Half Moon Bay / SR-92 East / Fashion Island Blvd","3rd Avenue","Poplar Avenue",
			                    "Anza Blvd","Broadway","Milbrae Avenue", 
			                    "San Bruno Avenue / San Francisco Airport / North Access Roads/ Cargo Area",
			                    "I-380 West to I-280 / San Bruno","South Airport Blvd","Grand Avenue",
			                    "Oyster Point Blvd East","South San Francisco","Sierra Point Pkwy",
			                    "Monster Park / Tunnel Avenue","Third Street / Cow Palace","Paul Avenue",
			                    "Silver Avenue", "Daly City / I-280", "Cesar Chavez Street", "I-80 / Bay Bridge / Oakland",
			                    "Marina Blvd (Left Exit)","SR-1 South / 19th Ave (end shared alignment with SR-1)",
			                    "Lincoln Blvd / 25th Ave"};
		
		String[] fwy101N_Desc = {"SR-25 East","Monterey Street","SR-152 East / 10th Street / Pacheco Pass Rd",
				                "SR-152 West / Leavesley Road","Masten Avenue", "San Martin Avenue",
				                "Tennant Avenue","East Dunne Avenue","Cochrane Road", 
				                "Coyote Creek Golf Drive","Bailey Avenue", "SR-85 North / Cupertino / Mtn View",
				                "Silicon Valley Blvd / Bernal Road","SR-82 / Blossom Hill Rd / Silver Creek Valley Rd",
				                "Hellyer Avenue","Yerba Buena Road","Capitol Expressway","Tully Road",
				                "I-280 / San Jose / I-680 / Sacramento","Story Road",
				                "SR-130 East / Alum Rock Ave / Santa Clara Street","McKee Road / Julian Street",
				                "Oakland Road / 13th Street","I-880 North / Oakland",
				                "I-880 South / Santa Cruz","Old Bayshore Highway","Brokaw Road / First Street",
				                "Trimble Road / De La Cruz Blvd",
				                "Montague Expressway / San Tomas Expressway","Great America Parkway / Bowers Avenue",
				                "Lawrence Expressway","Fair Oaks Avenue","Mathilda Avenue North / SR-237 East",
				                "Mathilda Avenue South","SR-237 West / Mountain View Road / Alviso Road",
				                "Ellis Street / Moffett South Gate","Moffett Blvd / NASA Pkwy",
				                "Shoreline Blvd","Old Middlefield Way","Amphitheater Parkway",
				                "Rengstorff Avenue","San Antonio Road",
				                "Embarcadero Road / Oregon Expressway","University Avenue","Willow Road East",
				                "Willow Road West","SR-84 East / Marsh Road",
				                "SR-84 West / Woodside Road","Whipple Avenue","Holly Street / Redwood Shores Pkwy",
				                "Ralston Avenue / Marine Parkway","Hillsdale Blvd / Foster City",
				                "SR-92 West / Half Moon Bay / SR-92 East / Hayward / San Mateo","Kehoe Avenue","3rd Avenue","Dore Avenue",
				                "Peninsula Avenue","Anza Blvd","Broadway","Milbrae Avenue", 
				                "San Francisco Airport","San Bruno Avenue",
				                "I-380 West to I-280 / San Bruno","South Airport Blvd","Grand Avenue",
				                "Oyster Point Blvd","Bayshore Blvd / Cow Palace","Sierra Point Pkwy",
				                "Monster Park","Third Street","Paul Avenue",
				                "Daly City / Port of S.F. / I-280","Silver Avenue","Alemany Blvd / Bayshore Blvd",
				                "Cesar Chavez Street / Potrero Avenue", "Vermont Street","I-80 / Bay Bridge / Oakland",
				                "Ninth Street / Civic Center","Duboce Ave","Octavia Street / Fell Street",
				                "SR-1 South / 19th Ave (begin shared alignment with SR-1)",
				                "Lincoln Blvd"};
			
		ArrayList<List<Double>> latlong;
		HashMap<Double, Double> fwy101N_LatLongs = new HashMap<Double, Double>();
		HashMap<Double, Double> fwy101S_LatLongs = new HashMap<Double, Double>();

		//add Exits for fwy101N/S data structure
		fwy101S.setArrayOfExits(fwy101S_Exits);
		fwy101S.printExitList();
		fwy101S.setExitDescriptionArray(fwy101S_Exits, fwy101S_Desc);
		fwy101S.printDescriptionList();
		fwy101S.printExitAndDescriptionList();
		latlong = fwy101S.latLongLoader();		
	}

}
