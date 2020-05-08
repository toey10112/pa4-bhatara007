import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class GraphData {

    public GraphData() {
    }

    public ArrayList<String> getDate() throws Exception {
        ArrayList<String> date = new ArrayList<>();

        URL oracle = new URL("https://covid.ourworldindata.org/data/ecdc/total_cases.csv");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;
        int i = 0;
        while ((inputLine = in.readLine()) != null){
            if (i >= 1) {
                date.add(inputLine.split(",")[0]);
            }
            i++;
        }

        return date;
    }

    public String[] getWorldData(String where,String date) throws Exception {
        ArrayList<String> allData = new ArrayList<>();

        URL oracle = new URL("https://covid.ourworldindata.org/data/ecdc/full_data.csv");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;
        int i = 0;
        while ((inputLine = in.readLine()) != null){
            if (inputLine.split(",")[0].equalsIgnoreCase(date) && inputLine.split(",")[1].equalsIgnoreCase(where)) {
                allData.add(inputLine);
            }
        }
        String[] worldData = allData.get(allData.size() - 1).split(",");

        return worldData;
    }


    public ArrayList<String> getCountryConfirmCase(String type,String country) throws Exception{

        String url = "https://covid.ourworldindata.org/data/ecdc/new_deaths.csv";

        if(type.equals("Total confirmed cases")) url = "https://covid.ourworldindata.org/data/ecdc/total_cases.csv";
        else if(type.equals("Total deaths")) url = "https://covid.ourworldindata.org/data/ecdc/total_deaths.csv";
        else if(type.equals( "New confirmed cases"))url = "https://covid.ourworldindata.org/data/ecdc/new_cases.csv";

        String[] c = new String[0];
        ArrayList<String> cc= new ArrayList<>();
        int index = 0;

        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;

        if((inputLine = in.readLine()) != null){
            c = inputLine.split(",");
        }

        for(int i = 0; i < c.length; i++){
            if(c[i].equalsIgnoreCase(country)){
                index = i;
            }
        }
        int i = 0;
        while((inputLine = in.readLine()) != null) {
            try{
                i = Integer.parseInt(inputLine.split(",")[index]);

            }catch (Exception e){
            }

            try {
                if (inputLine.split(",")[index].isEmpty()) {
                    cc.add(String.valueOf(i));
                } else {
                    cc.add(inputLine.split(",")[index]);
                }
            }catch (Exception e){

            }
        }
        return cc;
    }


    public String[] getCountry() throws IOException {

        String[] s = new String[0];
        URL oracle = new URL("https://covid.ourworldindata.org/data/ecdc/total_cases.csv");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;

        if ((inputLine = in.readLine()) != null) {
            String ss = inputLine.substring(5,inputLine.length());
            s = ss.split(",");
        }
        return s;
    }

    public static void main(String[] args) throws Exception {

    }

}

