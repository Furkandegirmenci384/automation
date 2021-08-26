# Odeon Reservation Monitor Module Regression

// 17, 18, 22, 36 Önceki patlayan
// 17, 34(Tek geçiyor)

## TC1 - Reservation Monitor ekranında Search butonuna tıklanıldığında search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Reservation Date" alanında start date day "1" end date day "30" olarak seçilir.
* "Hotel Name" alanına "Club Capa Hotel" değeri girilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Tabloda "Reservation Date" rowunda tarih aralığı kontrol edilir.
* Tabloda "Hotel Name" rowunda "Club Capa Hotel" datasının bulunduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC2 - Reservation Monitor ekranında Clear butonuna tıklanıldığında uygulanan filtreler sıfırlanmalı
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Reservation Date" alanında start date day "1" end date day "30" olarak seçilir.
* "Check-in Date" alanında start date day "17" end date day "24" olarak seçilir.
* "Check-out Date" alanında start date day "20" end date day "3" olarak seçilir.
* "Cancellation Date" alanında start date day "13" end date day "22" olarak seçilir.
* "Hotel Name" alanına "Ramada Hotel & Suites by Wyndham Istanbul Golden Horn, Istanbul, Turkey" değeri girilir.
* Sayfada "Clear" butonuna tıklanır.
* "Reservation Date" başlığında start date alanında data olmadığı kontrol edilir.
* "Check-in Date" başlığında start date alanında data olmadığı kontrol edilir.
* "Check-out Date" başlığında start date alanında data olmadığı kontrol edilir.
* "Cancellation Date" başlığında start date alanında data olmadığı kontrol edilir.
* "Reservation Date" başlığında end date alanında data olmadığı kontrol edilir.
* "Check-in Date" başlığında end date alanında data olmadığı kontrol edilir.
* "Check-out Date" başlığında end date alanında data olmadığı kontrol edilir.
* "Cancellation Date" başlığında end date alanında data olmadığı kontrol edilir.
* "Hotel Name" alanında data olmadığı kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC3 - Reservation Monitor sayfasında ki Fieldy Type'ı MultiSelection olan alanlarda çoklu seçim yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Reservation Status" dropdown menüsünde verilerin geldiği doğrulanır.
* "Reservation Status" dropdown menuden "Confirmed" değeri seçilir.
* "Reservation Status" dropdown menuden "Cancelled" değeri seçilir.
* "Reservation Status" dropdown menuden "Cancellation Incomplete" değeri seçilir.
* "Reservation Status" dropdown menuden "Pending" değeri seçilir.
* "Reservation Status" dropdown menuden "Failed" değeri seçilir.
* "Reservation Status" dropdown menuden "Confirmed" değeri seçildiği kontrol edilir.
* "Reservation Status" dropdown menuden "Cancelled" değeri seçildiği kontrol edilir.
* "Reservation Status" dropdown menuden "Cancellation Incomplete" değeri seçildiği kontrol edilir.
* "Reservation Status" dropdown menuden "Pending" değeri seçildiği kontrol edilir.
* "Reservation Status" dropdown menuden "Failed" değeri seçildiği kontrol edilir.
* "Reservation Sub-status" dropdown menüsünde verilerin geldiği doğrulanır.
* "Reservation Sub-status" dropdown menuden "Not Refunded" değeri seçilir.
* "Reservation Sub-status" dropdown menuden "Refunded" değeri seçilir.
* "Reservation Sub-status" dropdown menuden "Cancellation Profit" değeri seçilir.
* "Reservation Sub-status" dropdown menuden "Not Refunded" değeri seçildiği kontrol edilir.
* "Reservation Sub-status" dropdown menuden "Refunded" değeri seçildiği kontrol edilir.
* "Reservation Sub-status" dropdown menuden "Cancellation Profit" değeri seçildiği kontrol edilir.
* "Refund Information" dropdown menüsünde verilerin geldiği doğrulanır.
* "Refund Information" dropdown menuden "Refundable" değeri seçilir.
* "Refund Information" dropdown menuden "Non-refundable" değeri seçilir.
* "Refund Information" dropdown menuden "Refundable" değeri seçildiği kontrol edilir.
* "Refund Information" dropdown menuden "Non-refundable" değeri seçildiği kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC4 - Reservation sayfasında ki Select All seçimi olan alanlarda Select All seçimine tıklanabilmeli ve tıklandığında tüm veriler seçilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Sale Channel" dropdown menuden "SelectAll" değeri seçilir.
* "Sale Channel" dropdown menuden "B2B" değeri seçildiği kontrol edilir.
* "Sale Channel" dropdown menuden "B2C" değeri seçildiği kontrol edilir.
* "Sale Channel" dropdown menuden "API" değeri seçildiği kontrol edilir.
* "Loss Type" dropdown menuden "SelectAll" değeri seçilir.
* "Loss Type" dropdown menuden "Mismatch Loss" değeri seçildiği kontrol edilir.
* "Loss Type" dropdown menuden "Technical Loss" değeri seçildiği kontrol edilir.
* "Loss Type" dropdown menuden "Operational Loss" değeri seçildiği kontrol edilir.
* "Loss Type" dropdown menuden "Open For Dispute" değeri seçildiği kontrol edilir.
* "Loss Type" dropdown menuden "Complimentary" değeri seçildiği kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC5 - Reservation Monitor ekranında Reservation Number alanına tıklanabilmeli ve veri girişi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Reservation Number" alanına "7BIIBSZ4D" veri girişi yapılır.
* Sayfada "Search" butonuna tıklanır.
* Tabloda "Reservation Number" rowunda "7BIIBSZ4D" datasının bulunduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC6 - Reservation Monitor ekranında PI Code alanına tıklanabilmeli ve veri girişi yapılarak search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "PI Code" alanına "7BIIBSZ4D_11_1" veri girişi yapılır.
* Sayfada "Search" butonuna tıklanır.
* Tabloda "Reservation Number" rowunda "7BIIBSZ4D" datasının bulunduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC7 - Reservation Monitor ekranında Confirmation Number alanına tıklanabilmeli ve veri girişi yapılarak search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Confirmation No" alanına "4034645" veri girişi yapılır.
* Sayfada "Search" butonuna tıklanır.
* Tabloda "Reservation Number" rowunda "TOJH57E6A" datasının bulunduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC8 - Reservation Monitor ekranında Reservation Date alanına tıklanabilmeli ve tarihlerin bulunduğu popup'dan tarih seçimi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Reservation Date" alanında start date alanına tıklanır.
* Tarihlerin bulunduğu pop-up'ın açıldığı kontrol edilir.
* "Reservation Date" alanında start date day "8" end date day "21" olarak seçilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Tabloda "Reservation Date" rowunda tarih aralığı kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC9 - Reservation Monitor ekranında Check-in Date alanına tıklanabilmeli ve tarihlerin bulunduğu popup'dan tarih seçimi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Check-in Date" alanında start date alanına tıklanır.
* Tarihlerin bulunduğu pop-up'ın açıldığı kontrol edilir.
* "Check-in Date" alanında start date day "17" end date day "24" olarak seçilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Tabloda "Check-in Date" rowunda tarih aralığı kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC10 - Reservation Monitor ekranında Check-Out Date alanına tıklanabilmeli ve tarihlerin bulunduğu popup'dan tarih seçimi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Check-out Date" alanında start date alanına tıklanır.
* Tarihlerin bulunduğu pop-up'ın açıldığı kontrol edilir.
* "Check-out Date" alanında start date day "20" end date day "30" olarak seçilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Tabloda "Check-out Date" rowunda tarih aralığı kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

//## TC11 - Reservation Monitor ekranında Cancellation Date alanına tıklanabilmeli ve tarihlerin bulunduğu popup'dan tarih seçimi yapılabilmeli
//* "http://192.168.186.88:81/reservationdetail" urline gidilir.
//* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* "Cancellation Date" alanında start date alanına tıklanır.
//* Tarihlerin bulunduğu pop-up'ın açıldığı kontrol edilir.
//* "Cancellation Date" alanında start date day "8" end date day "22" olarak seçilir.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda dataların geldiği doğrulanır.
//* Datagrid alanında bulunan column chooser butonuna tıklanır.
//* Datagrid alanında column chooser pop-up'ının açıldığı kontrol edilir.
//* Column Chooser pop-up'ında "Cancellation Date" checkbox'ı işaretlenir.
//* Column Chooser pop-up'ı kapatılır.
//* Tabloda "Cancellation Date" alanının olduğu doğrulanır.
//* Tabloda "Cancellation Date" rowunda tarih aralığı kontrol edilir.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.


////CANCELLATION PENALTY DATE COLUMN CHOOSER YOK
//## TC12 - Reservation Monitor ekranında Cancellation Penalty Date alanına tıklanabilmeli ve tarihlerin bulunduğu popup'dan tarih seçimi yapılabilmeli
//* "http://192.168.186.88:81/reservationdetail" urline gidilir.
//* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* "Cancellation Penalty Date" alanında start date alanına tıklanır.
//* Tarihlerin bulunduğu pop-up'ın açıldığı kontrol edilir.
//* "Cancellation Penalty Date" alanında start date day "13" end date day "22" olarak seçilir.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda dataların geldiği doğrulanır.
////* Tabloda "Cancellation Penalty Date" rowunda tarih aralığı kontrol edilir.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC13 - Check-in Date tarih araliginda Start Date seçilip End Date alanı null birakilirsa, Start Date - 00:00:00'dan gunumuz tarih ve saatine kadar butun reservation'lari getirmesi beklenir.
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Check-in Date" alanında start date day "16" olarak seçilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Tabloda "Check-in Date" rowunda tarih aralığı kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC14 - Reservation Monitor ekranında  Hotel Name search input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Hotel Name" alanına "Aspen Hotel - Special Class" değeri girilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Tabloda "Hotel Name" rowunda "Aspen Hotel - Special Class" datasının bulunduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.


 HOTEL DESTINATION DATASI TUTARLI GELMİYOR TEKRAR BAKILACAK

////## TC15 - Reservation Monitor ekranında  Hotel Destination search input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
////* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
////* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
////* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
////* Sayfada "Submit" butonuna tıklanır.
////* OdeonBeds logosunun geldiği kontrol edilir.
////* "Hotel Destination" alanına "Kaleici" değeri girilir.
////* Sayfada "Search" butonuna tıklanır.
////* Tabloda dataların geldiği doğrulanır.
////* Sayfada "Sign Out" butonuna tıklanır.
////* Sign Out pop-up'ının açıldığı kontrol edilir.
////* Sayfada "OK" butonuna tıklanır.
////* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC16 - Reservation Monitor ekranında Hotel Country search input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Supplier Hotel Country" alanına "Turkey" değeri girilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC17 - Reservation Monitor ekranında Supplier search input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Supplier" dropdown menuye tıklanır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Supplier" dropdown alanına "O" veri girişi yapılır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Supplier" dropdown alanına "Odeon" veri girişi yapılır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Supplier" dropdown menuden "Odeonline" değeri seçilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Datagrid alanında bulunan column chooser butonuna tıklanır.
* Datagrid alanında column chooser pop-up'ının açıldığı kontrol edilir.
* Column Chooser pop-up'ında "Supplier" checkbox'ı işaretlenir.
* Column Chooser pop-up'ı kapatılır.
* Tabloda "Supplier" alanının olduğu doğrulanır.
* Tabloda "Supplier" rowunda "Odeonline" datasının bulunduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC18 - Reservation Monitor ekranında  Sale Channel  input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Sale Channel" dropdown menuye tıklanır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Sale Channel" dropdown alanına "B" veri girişi yapılır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Sale Channel" dropdown menuden "B2B" değeri seçilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Datagrid alanında bulunan column chooser butonuna tıklanır.
* Datagrid alanında column chooser pop-up'ının açıldığı kontrol edilir.
* Column Chooser pop-up'ında "Sale Channel" checkbox'ı işaretlenir.
* Column Chooser pop-up'ı kapatılır.
* Tabloda "Sale Channel" alanının olduğu doğrulanır.
* Tabloda "Sale Channel" rowunda "B2B" datasının bulunduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC19 - Reservation Monitor ekranında  Buyer Name  input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Buyer Name" dropdown menuye tıklanır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Buyer Name" dropdown alanına "I" veri girişi yapılır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Buyer Name" dropdown alanına "Istanbul" veri girişi yapılır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Buyer Name" dropdown menuden "Istanbul GSA" değeri seçilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Datagrid alanında bulunan column chooser butonuna tıklanır.
* Datagrid alanında column chooser pop-up'ının açıldığı kontrol edilir.
* Column Chooser pop-up'ında "Buyer Name" checkbox'ı işaretlenir.
* Column Chooser pop-up'ı kapatılır.
* Tabloda "Buyer Name" alanının olduğu doğrulanır.
* Tabloda "Buyer Name" rowunda "Istanbul GSA" datasının bulunduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC20 - Reservation Monitor ekranında  Guest Name   alanına veri girişi yapılarak search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Guest Name" alanına "filiz" veri girişi yapılır.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Tabloda "Guest Name" rowunda "filiz" datasının bulunduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC21 - Reservation Monitor ekranında  Guest Surname   alanına veri girişi yapılarak search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Guest Surname" alanına "topal" veri girişi yapılır.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Tabloda "Guest Surname" rowunda "topal" datasının bulunduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC22 - Reservation Monitor ekranında  Nationality   input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Nationality" dropdown menuye tıklanır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Nationality" dropdown alanına "R" veri girişi yapılır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Nationality" dropdown alanına "Russian" veri girişi yapılır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Nationality" dropdown alanına "Russian Fede" veri girişi yapılır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Nationality" dropdown menuden "Russian Federation" değeri seçilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Datagrid alanında bulunan column chooser butonuna tıklanır.
* Datagrid alanında column chooser pop-up'ının açıldığı kontrol edilir.
* Column Chooser pop-up'ında "Nationality" checkbox'ı işaretlenir.
* Column Chooser pop-up'ı kapatılır.
* Tabloda "Nationality" alanının olduğu doğrulanır.
* Tabloda "Nationality" rowunda "Russian Federation" datasının bulunduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC23 - Reservation Monitor ekranında  Reservation Status   input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Reservation Status" dropdown menuye tıklanır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Reservation Status" dropdown alanına "C" veri girişi yapılır.
* Dropdown menuden "Cancelled" değeri seçilir.
* Dropdown menuden "Confirmed" değeri seçilir.
* "Reservation Status" dropdown alanına "F" veri girişi yapılır.
* Dropdown menuden "Failed" değeri seçilir.
* "Reservation Status" dropdown alanına "P" veri girişi yapılır.
* Dropdown menuden "Pending" değeri seçilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC24 - Reservation Monitor ekranında  Reservation Sub-Status    input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Reservation Sub-status" dropdown menuye tıklanır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Reservation Sub-status" dropdown alanına "N" veri girişi yapılır.
* Dropdown menuden "Not Refunded" değeri seçilir.
* "Reservation Sub-status" dropdown alanına "R" veri girişi yapılır.
* Dropdown menuden "Refunded" değeri seçilir.
* "Reservation Sub-status" dropdown alanına "C" veri girişi yapılır.
* Dropdown menuden "Cancellation Profit" değeri seçilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC25 - Reservation Monitor ekranında  Supplier Payment Status input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Supplier Payment Status" dropdown menuye tıklanır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Supplier Payment Status" dropdown alanına "P" veri girişi yapılır.
* Dropdown menuden "Paid" değeri seçilir.
* "Supplier Payment Status" dropdown alanına "N" veri girişi yapılır.
* Dropdown menuden "Not Paid" değeri seçilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC26 - Reservation Monitor ekranında Buyer Payment Status input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Buyer Payment Status" dropdown menuye tıklanır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Buyer Payment Status" dropdown alanına "P" veri girişi yapılır.
* Dropdown menuden "Paid" değeri seçilir.
* "Buyer Payment Status" dropdown alanına "N" veri girişi yapılır.
* Dropdown menuden "Not Paid" değeri seçilir.
* "Buyer Payment Status" dropdown alanına "P" veri girişi yapılır.
* Dropdown menuden "Paid (Credit)" değeri seçilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC27 - Reservation Monitor ekranında  Refund Information  input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Refund Information" dropdown menuye tıklanır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Refund Information" dropdown alanına "R" veri girişi yapılır.
* Dropdown menuden "Refundable" değeri seçilir.
* "Refund Information" dropdown alanına "N" veri girişi yapılır.
* Dropdown menuden "Non-refundable" değeri seçilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC28 - Reservation Monitor ekranında Loss Type  input alanına tıklanabilmeli, girilen her karaktere göre search işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Loss Type" dropdown menuye tıklanır.
* Dropdown menude verilerin geldiği kontrol edilir.
* "Loss Type" dropdown alanına "M" veri girişi yapılır.
* Dropdown menuden "Mismatch Loss" değeri seçilir.
* "Loss Type" dropdown alanına "T" veri girişi yapılır.
* Dropdown menuden "Technical Loss" değeri seçilir.
* "Loss Type" dropdown alanına "O" veri girişi yapılır.
* Dropdown menuden "Operational Loss" değeri seçilir.
* Dropdown menuden "Open For Dispute" değeri seçilir.
* "Loss Type" dropdown alanına "C" veri girişi yapılır.
* Dropdown menuden "Complimentary" değeri seçilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC29 - Reservation Monitor ekranındaki Field Type'i Date olan alanlarda Start date seçimi yapılırken End date seçiminden sonraki bir tarih seçimine izin verilmemeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
//* "Reservation Date" alanında start date day "3" end date day "5" olarak seçilir.
* "Reservation Date" alanında start date day "3" olarak seçilir.
* "Reservation Date" alanında end date day "5" olarak seçilir.
* "Reservation Date" alanında start date day "6" olarak seçilir.
* "Reservation Date" başlığında end date alanında data olmadığı kontrol edilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Tabloda "Reservation Date" rowunda tarih aralığı kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC30 - Reservation Monitor ekranındaki Field Type'i Date olan alanlarda End date seçimi yapılırken Start date seçiminden önceki bir tarih seçimine izin verilmemeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Reservation Date" alanında start date day "5" end date day "10" olarak seçilir.
* "Reservation Date" alanında start date day "16" olarak seçilir.
* End date alanında tarih günü "16" öncesi seçilemediği kontrol edilir.
* Sayfada "Search" butonuna tıklanır.
* Tabloda dataların geldiği doğrulanır.
* Tabloda "Reservation Date" rowunda tarih aralığı kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC31 - Reservation Monitor ekranındaki Field Type'i Date olan alanlarda bulunan "X" butonuna tıklanılabilmeli ve seçilen tarih silinebilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Reservation Date" alanında start date day "16" end date day "20" olarak seçilir.
* "Reservation Date" date alanında close ikonuna tıklanır.
* "Reservation Date" başlığında start date alanında data olmadığı kontrol edilir.
* "Reservation Date" başlığında end date alanında data olmadığı kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC32 - Reservation Monitor ekranında datagrid alanında ki refresh butonuna tıklanabilmeli ve data grid alanı default değerlerine dönmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Reservation Date" alanında start date day "16" end date day "20" olarak seçilir.
* "Hotel Name" alanına "Clup Capa" değeri girilir.
* "Loss Type" dropdown alanına "T" veri girişi yapılır.
* Dropdown menuden "Technical Loss" değeri seçilir.
* "Reservation Sub-status" dropdown alanına "N" veri girişi yapılır.
* Dropdown menuden "Not Refunded" değeri seçilir.
* Sayfada "Search" butonuna tıklanır.
* Datagrid alanında bulunan refresh butonuna tıklanır.
* "Reservation Date" başlığında start date alanında data olmadığı kontrol edilir.
* "Reservation Date" başlığında end date alanında data olmadığı kontrol edilir.
* Search inputlarında data olmadığı kontrol edilir.
* Dropdown inputlarında data olmadığı kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC33 - Reservation Monitor ekranında Data grid toolbarda ki export excel butonuna tıklanabilmeli ve excel olarak indirme işlemi yapılabilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* Export Excel butonuna tıklanır.
* Excel "reservation" dosyasının doğru uzantıda olduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC34 - İndirilen Excel Dosyasının isim formatı reservation-BulunulanGününTarihi-ListelemeYapılanSaat.xlsx olarak tanımlanmalı
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Loss Type" dropdown menuden "Mismatch Loss" değeri seçilir.
* "Loss Type" dropdown menuden "Mismatch Loss" değeri seçildiği kontrol edilir.
* Sayfada "Search" butonuna tıklanır.
* Datagrid alanında bulunan column chooser butonuna tıklanır.
* Datagrid alanında column chooser pop-up'ının açıldığı kontrol edilir.
* Column Chooser pop-up'ında "Loss Type" checkbox'ı işaretlenir.
* Column Chooser pop-up'ı kapatılır.
* Tabloda "Loss Type" alanının olduğu doğrulanır.
* Tabloda "Loss Type" rowunda "Mismatch Loss" datasının bulunduğu kontrol edilir.
* Export Excel butonuna tıklanır.
* Excel "reservation" dosyasının doğru uzantıda olduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

//## TC35 - İndirilen Excel Dosyasının içeriğinde Türkçe ve Özel karakterler olduğunda Excel dosyasının içeriğinde bu karakterler gözükmeli ve dosya içeriği bozulmamalı

## TC36 - İndirilen Excel Dosyasında bulunan veriler Reservation Monitor sayfasında bulunan datagrid ile birebir aynı olmalı
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Loss Type" dropdown menuden "Mismatch Loss" değeri seçilir.
* "Loss Type" dropdown menuden "Mismatch Loss" değeri seçildiği kontrol edilir.
* Sayfada "Search" butonuna tıklanır.
* Datagrid alanında bulunan column chooser butonuna tıklanır.
* Datagrid alanında column chooser pop-up'ının açıldığı kontrol edilir.
* Column Chooser pop-up'ında "Loss Type" checkbox'ı işaretlenir.
* Column Chooser pop-up'ı kapatılır.
* Tabloda "Loss Type" alanının olduğu doğrulanır.
* Tabloda "Loss Type" rowunda "Mismatch Loss" datasının bulunduğu kontrol edilir.
* Export Excel butonuna tıklanır.
* Excel "reservation" dosyasının uzantısı kontrol edilir ve içeriğinin datagrid yapısıyla aynı olduğu kontrol edilir.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC37 - Reservation Monitor ekranında ki çoklu seçim inputlarda ki "X" butonuna tıklanabilmeli ve veriler çoklu silinebilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* "Loss Type" dropdown menuye tıklanır.
* "Loss Type" dropdown menude "Mismatch Loss" verisi olduğu kontrol edilir.
* "Loss Type" dropdown menude "Technical Loss" verisi olduğu kontrol edilir.
* "Loss Type" dropdown menude "Operational Loss" verisi olduğu kontrol edilir.
* "Loss Type" dropdown menude "Open For Dispute" verisi olduğu kontrol edilir.
* "Loss Type" dropdown menude "Complimentary" verisi olduğu kontrol edilir.
* "Loss Type" dropdown menuden "SelectAll" değeri seçilir.
* "Loss Type" dropdown alanında close ikonuna tıklanır.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.

## TC38 - Data grid toolbarda ki column chooser butonuna tıklanabilmeli ve filtre seçilebilmeli
* "http://192.168.186.88:81/reservationdetail" urline gidilir.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
* Sayfada "Submit" butonuna tıklanır.
* OdeonBeds logosunun geldiği kontrol edilir.
* Datagrid alanında bulunan column chooser butonuna tıklanır.
* Datagrid alanında column chooser pop-up'ının açıldığı kontrol edilir.
* Column Chooser pop-up'ında "PI Code" checkbox'ı işaretlenir.
* Column Chooser pop-up'ı kapatılır.
* Tabloda "PI Code" alanının olduğu doğrulanır.
* Sayfada "Sign Out" butonuna tıklanır.
* Sign Out pop-up'ının açıldığı kontrol edilir.
* Sayfada "OK" butonuna tıklanır.
* "http://192.168.186.88:81/reservationdetail" urlinin açıldığı doğrulanır.
