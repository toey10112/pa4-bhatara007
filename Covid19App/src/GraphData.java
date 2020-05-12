import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.function.DoubleToIntFunction;

/**
 * Class for get all data for display in Covid19-Tracker Application.
 *
 * @author Bhatara Chaemchan Ske17
 */
public class GraphData {
    /**
     * Method for get date for display.
     *
     * @return date ArrayList of date.
     * @throws Exception when URL not found.
     */
    public ArrayList<String> getDate() throws Exception {
        ArrayList<String> date = new ArrayList<>();

        URL oracle = new URL("https://covid.ourworldindata.org/data/ecdc/total_cases.csv");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;
        int i = 0;
        while ((inputLine = in.readLine()) != null) {
            if (i >= 1) {
                date.add(inputLine.split(",")[0]);
            }
            i++;
        }

        return date;
    }

    /**
     * Method that return a data of country and date for display from URL.
     *
     * @param where the location of data
     * @param date  the date of data
     * @return worldData List of confirm cases, new cases, new deaths and total death for display.
     * @throws Exception when URL not found.
     */
    public String[] getWorldData(String where, String date) throws Exception {
        ArrayList<String> allData = new ArrayList<>();

        URL oracle = new URL("https://covid.ourworldindata.org/data/ecdc/full_data.csv");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;
        int i = 0;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.split(",")[0].equalsIgnoreCase(date) && inputLine.split(",")[1].equalsIgnoreCase(where)) {
                allData.add(inputLine);
            }
        }
        String[] worldData = allData.get(allData.size() - 1).split(",");

        return worldData;
    }

    /**
     * Method that return a confirm cases, new cases, new deaths and total death for display from URL.
     *
     * @param type    a type of data.
     * @param country a country of data.
     * @return ArrayList of confirm cases, new cases, new deaths and total death for display.
     * @throws Exception when URL not found.
     */
    public static ArrayList<String> getCountryConfirmCase(String type, String country) throws Exception {

        String url = "https://covid.ourworldindata.org/data/ecdc/new_deaths.csv";

        if (type.equals("Total confirmed cases")) url = "https://covid.ourworldindata.org/data/ecdc/total_cases.csv";
        else if (type.equals("Total deaths")) url = "https://covid.ourworldindata.org/data/ecdc/total_deaths.csv";
        else if (type.equals("New confirmed cases")) url = "https://covid.ourworldindata.org/data/ecdc/new_cases.csv";

        String[] c = new String[0];
        ArrayList<String> data = new ArrayList<>();
        int index = 0;

        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;

        if ((inputLine = in.readLine()) != null) {
            c = inputLine.split(",");
        }

        for (int i = 0; i < c.length; i++) {
            if (c[i].equalsIgnoreCase(country)) {
                index = i;
            }
        }
        int i = 0;
        while ((inputLine = in.readLine()) != null) {
            try {
                if (!(inputLine.split(",")[index]).isEmpty()) {
                    i = Integer.parseInt(inputLine.split(",")[index]);
                }

                if (inputLine.split(",")[index].isEmpty()) {
                    data.add(String.valueOf(i));
                } else {
                    data.add(inputLine.split(",")[index]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                data.add("0");
            }
        }
        return data;
    }


    /**
     * Method that return a country named.
     *
     * @return data a List of Country named.
     * @throws IOException when URL not found.
     */
    public String[] getCountry() throws IOException {

        String[] data = new String[0];
        URL oracle = new URL("https://covid.ourworldindata.org/data/ecdc/total_cases.csv");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;

        if ((inputLine = in.readLine()) != null) {
            String ss = inputLine.substring(5, inputLine.length());
            data = ss.split(",");
        }
        return data;
    }
}

