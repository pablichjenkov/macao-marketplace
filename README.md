## Macao SUI
Macao SUI is a Server Driven UI(SDUI or SUI) application written purely with Compose Multiplatform.
<BR/>
Bellow is a video showcasing a short demo. In this demo there is a **Ktor Server Application** running locally that serves a `.json` file. This json file contains metadata that is used by a desktop application to render specific set of components. A desktop was selected for the video but it is available in Android, iOS and the Browser too.
<BR/>
The video bellow can be explained in 3 steps:
<BR/>
1. The json metadata is initiated with a Drawer as root navigation model. You can see how the App fetches the presentational metadata from the server and sets a Drawer as the root navigation component.
2. Then the json metadata is modified in the server to have a BottomNavigation as root navigation component. Soon as the App fetches the new metadata, you can see how a BottomNavigation appears in the App as root component . 
3. Then the server switches back to Drawer and when the App loads the new json file, again a Drawer is seen in the App.

<table border="0">
 <tr>
   <video width="620" src="https://github.com/pablichjenkov/macao-sdui-app/assets/5303301/da6410a8-c096-4489-9dc5-e85ebde77ed4" />
 </tr>
</table>

Also notice how the screens used for this demo, consume a public API provided by Amadeus Hotel and Flights.
<BR/>
Macao SUI SDK allows customers to create any type of Bussiness Logic Component, and present it with the navigation model of choice. Current navigation options are `Drawer`, `BottomNavigation`, `Panel`, `TopBar` and `Stack` but clients can create new navigation components as well as customized the existing ones.
