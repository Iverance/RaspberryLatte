 package edu.sjsu.cab.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.google.maps.model.LatLng;
import com.google.maps.model.LatLng; 
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

		LatLng[] fw101N_Exits = {new LatLng(36.96136,-121.5506),new LatLng(36.98678,-121.55844),new LatLng(37.00311,-121.55651),
								 new LatLng(37.02225,-121.56657),new LatLng(37.05936,-121.58454),new LatLng(37.08887,-121.5994),
								 new LatLng(37.11927,-121.6263),new LatLng(37.13159,-121.63358),new LatLng(37.13159,-121.63358),
								 new LatLng(37.19078,-121.69331),new LatLng(37.20993,-121.72335),new LatLng(37.24374,-121.77223),//371-377A
								 new LatLng(37.24125,-121.76784),new LatLng(37.25723,-121.7963),new LatLng(37.28183,-121.80846),//377B-380
								 new LatLng(37.24125,-121.76784),new LatLng(37.30319,-121.81679),new LatLng(37.31848,-121.83156),//381-383
								 new LatLng(37.33137,-121.84392),new LatLng(37.33589,-121.84841),new LatLng(37.3499,-121.8617),//384-386A
								 new LatLng(37.35414,-121.86608),new LatLng(37.35414,-121.86608),//386B-388A
								 new LatLng(37.3642,-121.90179),new LatLng(37.3642,-121.90179),new LatLng(37.36882,-121.909),//388B-389A
								 new LatLng(37.37175,-121.90179),//389B
								 new LatLng(37.37672,-121.94164),//391
								 new LatLng(37.382,-121.96394),new LatLng(37.38569,-121.9768),new LatLng(37.39115,-121.99585),//392-394
								 new LatLng(37.39565,-122.01276),new LatLng(37.39893,-122.02771),//395-396A
								 new LatLng(37.40066,-122.03559),new LatLng(37.40066,-122.03559),new LatLng(37.40402,-122.05095),//396B-397
								 new LatLng(37.40764,-122.06633),new LatLng(37.42833,-122.10141),new LatLng(37.42833,-122.10141),//398-399B
								 new LatLng(37.421,-122.09243),new LatLng(37.42833,-122.10141),new LatLng(37.42833,-122.10141),//400A-400C
								 new LatLng(37.44889,-122.12302),new LatLng(37.46047,-122.14089),new LatLng(37.46907,-122.15551),//402-404A
								 new LatLng(37.46907,-122.15551),new LatLng(37.48334,-122.18064),new LatLng(37.48897,-122.21292),//404B-408
								 new LatLng(37.49632,-122.23305),new LatLng(37.51393,-122.25611),new LatLng(37.52629,-122.27011),//409-412
								 new LatLng(37.54461,-122.28793),new LatLng(37.55338,-122.29614),new LatLng(37.56246,-122.30488),//414A-415
								 new LatLng(37.57086,-122.31398),new LatLng(37.58038,-122.32436),new LatLng(37.58434,-122.32861),//416-417B
								 new LatLng(37.58718,-122.35139),new LatLng(37.59003,-122.36118),new LatLng(37.60258,-122.38043),//419A-420
								 new LatLng(37.6128,-122.39585),new LatLng(37.63138,-122.4027),new LatLng(37.63605,-122.40375),//422-423B
								 new LatLng(37.63605,-122.40375),new LatLng(37.65,-122.40672),new LatLng(37.65656,-122.40637),//423C-425A
								 new LatLng(37.66385,-122.39901),new LatLng(37.67048,-122.39216),new LatLng(37.67356,-122.38995),//425B-426B
								 new LatLng(37.70868,-122.39505),new LatLng(37.71882,-122.39952),new LatLng(37.72356,-122.40114),//429A-429C
								 new LatLng(37.72906,-122.40333),new LatLng(37.73261,-122.4052),new LatLng(37.73533,-122.40682),//430A-431
								 new LatLng(37.74815,-122.40409),new LatLng(37.76266,-122.40544),new LatLng(37.76631,-122.40514),//432-433B
								 new LatLng(37.7719,-122.42339),new LatLng(37.78248,-122.42074),new LatLng(37.78293,-122.42082),//433C-434B
								 new LatLng(37.80401,-122.4716),new LatLng(37.80635,-122.47459)//438-439
				   };

		LatLng[] fw101S_Exits = {new LatLng(36.96136,-121.5506),new LatLng(36.98678,-121.55844),new LatLng(37.00311,-121.55651),
								 new LatLng(37.02225,-121.56657),new LatLng(37.05936,-121.58454),new LatLng(37.08887,-121.5994),
								 new LatLng(37.11927,-121.6263),new LatLng(37.13159,-121.63358),new LatLng(37.13159,-121.63358),
								 new LatLng(37.19078,-121.69331),new LatLng(37.20993,-121.72335),new LatLng(37.24374,-121.77223),//371-377A
								 new LatLng(37.24125,-121.76784),new LatLng(37.25723,-121.7963),new LatLng(37.28183,-121.80846),//377B-380
								 new LatLng(37.30319,-121.81679),new LatLng(37.31848,-121.83156),new LatLng(37.33973,-121.8521),//382-385A
								 new LatLng(37.3499,-121.8617),new LatLng(37.35414,-121.86608),new LatLng(37.3499,-121.8617),//386A-388A
								 new LatLng(37.3642,-121.90179),new LatLng(37.3642,-121.90179),//388B-388C
								 new LatLng(37.37175,-121.90179),new LatLng(37.37382,-121.92813),//389-390
								 new LatLng(37.37672,-121.94164),new LatLng(37.37672,-121.94164),//391A-391B*
								 new LatLng(37.382,-121.96394),new LatLng(37.38569,-121.9768),new LatLng(37.39115,-121.99585),//392-394
								 new LatLng(37.39565,-122.01276),new LatLng(37.39565,-122.01276),new LatLng(37.39893,-122.02771),//395A-396A
								 new LatLng(37.40066,-122.03559),new LatLng(37.40402,-122.05095),//396B-397
								 new LatLng(37.40764,-122.06633),new LatLng(37.40867,-122.06992),new LatLng(37.42833,-122.10141),//398A-399
								 new LatLng(37.421,-122.09243),new LatLng(37.42833,-122.10141),new LatLng(37.42833,-122.10141),//400A-400C
								 new LatLng(37.44889,-122.12302),new LatLng(37.46047,-122.14089),new LatLng(37.46907,-122.15551),//402-404A
								 new LatLng(37.46907,-122.15551),new LatLng(37.48334,-122.18064),new LatLng(37.48897,-122.21292),//404B-408
								 new LatLng(37.49632,-122.23305),new LatLng(37.51393,-122.25611),new LatLng(37.52629,-122.27011),//409-414B
								 new LatLng(37.54461,-122.28793),new LatLng(37.55338,-122.29614),//414A-414B
								 new LatLng(37.57086,-122.31398),new LatLng(37.58038,-122.32436),//416-417A
								 new LatLng(37.58718,-122.35139),new LatLng(37.59003,-122.36118),new LatLng(37.60258,-122.38043),//419A-420
								 new LatLng(37.63138,-122.4027),new LatLng(37.63605,-122.40375),//423A-423B
								 new LatLng(37.65,-122.40672),new LatLng(37.65656,-122.40637),//424,425A
								 new LatLng(37.66385,-122.39901),new LatLng(37.66884,-122.39369),new LatLng(37.67356,-122.38995),//425B-426
								 new LatLng(37.70868,-122.39505),new LatLng(37.71882,-122.39952),new LatLng(37.72356,-122.40114),//429A-430A
								 new LatLng(37.73261,-122.4052),new LatLng(37.73533,-122.40682),//430B,431
								 new LatLng(37.74815,-122.40409),new LatLng(37.76136,-122.40618),//432,433
								 new LatLng(37.80348,-122.45403),new LatLng(37.80342,-122.46998),new LatLng(37.80635,-122.47459)//437-439
		};

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
