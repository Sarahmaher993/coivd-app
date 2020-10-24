# coivd-app
An App to get the current statistics for covid 19.

Libraries used: 

1. Retrofit for network API calls.
2. Andriod jetpack (navigation componant, View Model).
* No DI library where used and manual injection was applied in the networking package.

Screens:

1. Home Screen: Display a map with pins for each country that have registered new COVID-19 cases in the past day. [Tracking API](https://covid19tracking.narrativa.com/index_en.html) was consumed with parameters current day and prevoius day sent as a date range.
Upon selecting a marker, a popup of country name and new confirmed cases appreas and once clicked it navigates to details screen.

2. Details Screen: Shows details of COVID-19 status in the selected country, the screen navigates to headlines screen.

3. News Screen: Consumes countries and news APIs in order to get country's health headlines, any article clicked navigates to the article link through the web browser.


 
