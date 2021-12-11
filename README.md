

# About The Project
Simple E-Commerce app **Koltin based** where you can review products under certain categories based on mocked json data
with **Mvvm Interactor Model**

# Project Demo
https://drive.google.com/file/d/1O9Pe6nygOQgHxsAaHbwGB5xa04IMq1n7/view?usp=sharing

# External Dependencies Used
1- Koin<br />
2- Pinview<br />
3- Glide<br />
4-Gson<br />
5- Navigation Component<br />

**Notes**<br />
1- navigation between screens we used Fragment Transaction and navigation component<br />
2- to login use any valid email or phone and password more than 6 characters <br />
3- otp code use 5555 for valid otp <br />
#   Deep Link
**Structure**<br />
  http://www.gtera_bassiouny.com/{categoryId}/{timestamp}/{minQuantity}/{maxQuantity}/{discount}<br />
**For example :**<br />
 http://www.gtera_bassiouny.com/5/1639921252/15/20/50<br />
 
 **Deep Link Note**<br />
 1- category id : number represent category id from 1 to 5<br />
 2- timestamp : unix time represent when the promotion start knowing that the end date is defined as 25-12-2021<br />
 3- minQuantity : minimum number of item<br />
 4- maxQuantity : maximum number of item<br />
 5- discount : the price value of discount<br />
 **if any of those parameters is invalid the whole link becomes invalid**<br />
 
**Example for invalid one** (used day before today)<br />
http://www.gtera_bassiouny.com/5/1639138160/15/20/50<br />
 
