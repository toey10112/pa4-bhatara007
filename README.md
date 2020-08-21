# Covid19 Tracker
## Introduction
Covid 19 tracker Application gives you real time Covid-19 disease data of more than 200 countries since 2020-1-1 as follows :
Total confirmed cases. 
New confirm cases 
New deaths
Total deaths 
This project focuses on updating real time Covid-19 disease data from online sources and learns how to create a good GUI interface from JavaFX.

## Main Features

- Get real time Covid19 disease data.
- Show pecentage different of Covid19 disease information day by day.
- Create historical line graphs of Covid19 disease data.


## How to run?

First you need to open **command prompt (for Windows)** or **terminal (for OSX)** and access CovidTracker.jar directory.

**for java sdk 8 type this command:**
> java -jar Covid19-Tracker.jar

**java sdk 11 or higher:**

> java --module-path (you javafx lib directory) --add-modules javafx.controls,javafx.fxml -jar Covid19-Tracker.jar

**Example:**

>java --module-path /Users/admin/Downloads/javafx-sdk-11.0.2/lib/ --add-modules javafx.controls,javafx.fxml -jar Covid19-Tracker.jar
## GUI Design

Menu              | World Summary           |  Charts             |
:---------------:|:----------:|:------------:
![](https://github.com/OOP2020/pa4-bhatara007/blob/master/photo/Screen%20Shot%202563-05-13%20at%2001.20.40.png)  |  ![](https://github.com/OOP2020/pa4-bhatara007/blob/master/photo/Screen%20Shot%202563-05-13%20at%2001.20.45.png) | ![](https://github.com/OOP2020/pa4-bhatara007/blob/master/photo/Screen%20Shot%202563-05-13%20at%2001.20.52.png)

## UML Diagram

![](https://github.com/OOP2020/pa4-bhatara007/blob/master/photo/UML.png)

## Technology Used
   - Java URL to get realtime data from online source.
   - JavaFX.fxml for design Ghaphic interface from Scene Builder Application.  
   - javafx.scene.chart to create BarChart and LineChart.
   - SceneBuilder for design GUI interface for application.

## Credit
[Coronavirus source data](https://ourworldindata.org/coronavirus-source-data)

The updated .csv files here:
- [Total confirmed cases](https://covid.ourworldindata.org/data/ecdc/total_cases.csv)
- [Total deaths](https://covid.ourworldindata.org/data/ecdc/total_deaths.csv)
- [New confirmed cases](https://covid.ourworldindata.org/data/ecdc/new_cases.csv)
- [New deaths](https://covid.ourworldindata.org/data/ecdc/new_deaths.csv)




