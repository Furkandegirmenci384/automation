# Odeon Markup Regression
//
//// 14 , 20
//
//## TC1 - Tüm alanların dolu olduğu durumda XML Search seçilerek markup oluşturma
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Create Markup" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/0/add" urlinin açıldığı doğrulanır.
//* "XML Search" search radio butonuna tıklanır.
//* "XML Search" search radio butonunun seçili olduğu kontrol edilir.
//* "Hotel Destination" alanına "Sisli, Istanbul, Turkey" değeri girilir.
//* "Hotel Destination" alanına "Adaklı, Bingol, Turkey" değeri girilir.
//* "Hotel Name" alanına "Listana Hotel, Istanbul, Turkey" değeri girilir.
//* "Chain Name" alanına "AC by Marriott" değeri girilir.
//* "Buyer Country" dropdown menuden "Select All" değeri seçilir.
//* "Buyer Group" dropdown menuden "SelcukTest" değeri seçilir.
//* "Buyer" dropdown menuden "Adem" değeri seçilir.
//* "Buyer" dropdown menuden "Filiz" değeri seçilir.
//* "Client Nationality" dropdown menuden "Canada" değeri seçilir.
//* "Channel" dropdown menusunden "B2B" değeri seçilir.
//* "Channel" dropdown menusunden "B2C" değeri seçilir.
//* "Supplier" dropdown menusunden "Select All" değeri seçilir.
//* Sayfada "AddNightlyGroup" butonuna tıklanır.
//* Nightlymin alanına "100" değeri girilir.
//* Nightlymax alanına "200" değeri girilir.
//* Sayfada "Add a Date Group" butonuna tıklanır.
//* Sadece Accommodation Date Between tarih seçiminin geldiği doğrulanır.
//* Accommodation Date alanında start date day "20" end date day "30" olarak seçilir.
//* Markup Type alanından "Base" seçilir.
//* Using Markup Type alanından "Fixed" seçilir.
//* Using Markup Type 2. alanından "Per Person" seçilir.
//* Markup Value alanına "100" değeri girilir.
//* Curreny alanından "USD" değeri seçilir.
//* Name alanına "Nejdet Arslan" değeri girilir.
//* Expire Date alanında start date day "20" end date day "30" olarak seçilir.
//* Is Active butonu disable hale getirilir.
//* Description alanına "VirgosolTest" değeri girilir.
//* Sayfada "Save" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Sayfada "Search" butonuna tıklanır.
////* Tabloda "Name" rowunda "Nejdet Arslan" kişisinin bulunduğu kontrol edilir.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
////Deleting Data
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* Sayfada "Search" butonuna tıklanır.
//* Datagrid alanında "Hotel Name" rowunda "Listana Hotel" olan datanın delete butonuna tıklanır.
//* Sayfada "Delete" butonuna tıklanır.
//* Delete pop-up içeriğinin "Are you sure to delete this markup?" olduğu doğrulanır.
//* Açılan pop-up'ta "Yes" ve "No" butonlarının bulunduğu kontrol edilir.
//* Sayfada "Yes" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC2 - Sadece zorunlu alanların dolu olduğu durumda Live Search seçilerek markup oluşturma
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Create Markup" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/0/add" urlinin açıldığı doğrulanır.
//* "Live Search" search radio butonuna tıklanır.
//* "Live Search" search radio butonunun seçili olduğu kontrol edilir.
//* Markup Type alanından "Base" seçilir.
//* Using Markup Type alanından "Fixed" seçilir.
//* Using Markup Type 2. alanından "Per Person" seçilir.
//* Markup Value alanına "100" değeri girilir.
//* Curreny alanından "USD" değeri seçilir.
//* Name alanına "Nejdet Arslan" değeri girilir.
//* Expire Date alanında start date day "20" end date day "30" olarak seçilir.
//* Is Active butonu aktif olduğu kontrol edilir.
//* Sayfada "Save" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Sayfada "Search" butonuna tıklanır.
////* Tabloda "Name" rowunda "Nejdet Arslan" kişisinin bulunduğu kontrol edilir.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
////Deleting Data
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* Sayfada "Search" butonuna tıklanır.
//* Datagrid alanında "Name" rowunda "Nejdet Arslan" olan datanın delete butonuna tıklanır.
//* Sayfada "Delete" butonuna tıklanır.
//* Delete pop-up içeriğinin "Are you sure to delete this markup?" olduğu doğrulanır.
//* Açılan pop-up'ta "Yes" ve "No" butonlarının bulunduğu kontrol edilir.
//* Sayfada "Yes" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC3 - Add markup sayfasında ki Save butonuna tıklanabilmeli ve kayıt işlemi yapılabilmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Create Markup" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/0/add" urlinin açıldığı doğrulanır.
//* "XML Search" search radio butonuna tıklanır.
//* "XML Search" search radio butonunun seçili olduğu kontrol edilir.
//* Markup Type alanından "Base" seçilir.
//* Using Markup Type alanından "Percentage" seçilir.
//* Using Markup Type 2. alanından "Per Unit" seçilir.
//* Markup Value alanına "100" değeri girilir.
//* Name alanına "Nejdet Arslan" değeri girilir.
//* Expire Date alanında start date day "20" end date day "30" olarak seçilir.
//* Is Active butonu aktif olduğu kontrol edilir.
//* Sayfada "Save" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Sayfada "Search" butonuna tıklanır.
////* Tabloda "Name" rowunda "Nejdet Arslan" kişisinin bulunduğu kontrol edilir.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
////Deleting Data
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* Sayfada "Search" butonuna tıklanır.
//* Datagrid alanında "Name" rowunda "Nejdet Arslan" olan datanın delete butonuna tıklanır.
//* Sayfada "Delete" butonuna tıklanır.
//* Delete pop-up içeriğinin "Are you sure to delete this markup?" olduğu doğrulanır.
//* Açılan pop-up'ta "Yes" ve "No" butonlarının bulunduğu kontrol edilir.
//* Sayfada "Yes" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC4 - Add markup sayfasında ki Back butonuna tıklanabilmeli ve Markup sayfasına yönlendirmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Create Markup" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/0/add" urlinin açıldığı doğrulanır.
//* "XML Search" search radio butonuna tıklanır.
//* "XML Search" search radio butonunun seçili olduğu kontrol edilir.
//* Sayfada "Back" butonunun tıklanabilir olduğu kontrol edilir.
//* Sayfada "Back" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC5 - Add markup sayfasında ki zorunlu alanlar doldurulmadan kaydetme işlemi yapıldığında zorunlu alanların kırmızı border ile zorunlu olduğu belirtilmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Create Markup" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/0/add" urlinin açıldığı doğrulanır.
//* "Live Search" search radio butonuna tıklanır.
//* "Live Search" search radio butonunun seçili olduğu kontrol edilir.
//* "Hotel Destination" alanına "Sisli, Istanbul, Turkey" değeri girilir.
//* "Hotel Destination" alanına "Adaklı, Bingol, Turkey" değeri girilir.
//* "Hotel Name" alanına "Listana Hotel, Istanbul, Turkey" değeri girilir.
//* "Chain Name" alanına "AC by Marriott" değeri girilir.
//* "Channel" dropdown menusunden "B2B" değeri seçilir.
//* "Channel" dropdown menusunden "B2C" değeri seçilir.
//* "Supplier" dropdown menusunden "Select All" değeri seçilir.
//* Sayfada "Save" butonuna tıklanır.
//* Zorunlu alanların doldurulması gerektiği uyarısının çıktığı kontrol edilir.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/0/add" urlinin açıldığı doğrulanır.
//
//// TEKRAR BAKILMADI MIN MAX DEĞER GIRILEBILIYOR  - DEĞERDE GİRİLEBİLİYOR
//
////## TC6 - Nightly Markup alanında ki Max değer Min değerden küçük girilememeli
////* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
////* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
////* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
////* Sayfada "Submit" butonuna tıklanır.
////* OdeonBeds logosunun geldiği kontrol edilir.
////* Sayfada "EN" butonuna tıklanır.
////* Sayfada "Create Markup" butonuna tıklanır.
////* "http://192.168.186.88:81/markup/0/add" urlinin açıldığı doğrulanır.
////* "Live Search" search radio butonuna tıklanır.
////* "Live Search" search radio butonunun seçili olduğu kontrol edilir.
////* Sayfada "AddNightlyGroup" butonuna tıklanır.
////* Nightlymin alanına "100" değeri girilir.
////* Nightlymax alanına "50" değeri girilir.
////* Nightlymax alanındaki değer silinir.
////* Nightlymax alanına "150" değeri girilir.
////* Markup Type alanından "Extra" seçilir.
////* Using Markup Type alanından "Fixed" seçilir.
////* Using Markup Type 2. alanından "Per Person" seçilir.
////* Markup Value alanına "10" değeri girilir.
////* Curreny alanından "TRY" değeri seçilir.
////* Name alanına "Nejdet Arslan" değeri girilir.
////* Expire Date alanında start date day "16" end date day "30" olarak seçilir.
////* Sayfada "Save" butonuna tıklanır.
////* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
////* Sayfada "Search" butonuna tıklanır.
////* Tabloda "Name" rowunda "Nejdet Arslan" kişisinin bulunduğu kontrol edilir.
////* Sayfada "Sign Out" butonuna tıklanır.
////* Sign Out pop-up'ının açıldığı kontrol edilir.
////* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
////* Sayfada "OK" butonuna tıklanır.
////* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
////
////## TC7 - Nightly Markup alanında ki Min ve Max değerler için eksi(-) değer girilememelidir
////* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
////* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
////* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
////* Sayfada "Submit" butonuna tıklanır.
////* OdeonBeds logosunun geldiği kontrol edilir.
////* Sayfada "EN" butonuna tıklanır.
////* Sayfada "Create Markup" butonuna tıklanır.
////* "http://192.168.186.88:81/markup/0/add" urlinin açıldığı doğrulanır.
////* "Live Search" search radio butonuna tıklanır.
////* "Live Search" search radio butonunun seçili olduğu kontrol edilir.
////* Sayfada "AddNightlyGroup" butonuna tıklanır.
////* Nightlymin alanına "-100" değeri girilir.
////* Nightlymax alanına "-50" değeri girilir.
////* Nightlymin alanındaki değer silinir.
////* Nightlymax alanındaki değer silinir.
////* Nightlymin alanına "-100" değeri girilir.
////* Nightlymax alanına "-50" değeri girilir.
////* Nightlymin alanındaki değer silinir.
////* Nightlymax alanındaki değer silinir.
////* Nightlymin alanına "100" değeri girilir.
////* Nightlymax alanına "150" değeri girilir.
////* Markup Type alanından "Extra" seçilir.
////* Using Markup Type alanından "Fixed" seçilir.
////* Using Markup Type 2. alanından "Per Person" seçilir.
////* Markup Value alanına "10" değeri girilir.
////* Curreny alanından "TRY" değeri seçilir.
////* Name alanına "Nejdet Arslan" değeri girilir.
////* Expire Date alanında start date day "16" end date day "30" olarak seçilir.
////* Sayfada "Save" butonuna tıklanır.
////* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
////* Sayfada "Search" butonuna tıklanır.
////* "4" saniye bekle
////* Tabloda "Name" rowunda "Nejdet Arslan" kişisinin bulunduğu kontrol edilir.
////* Sayfada "Sign Out" butonuna tıklanır.
////* Sign Out pop-up'ının açıldığı kontrol edilir.
////* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
////* Sayfada "OK" butonuna tıklanır.
////* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC8 - Markup Filter alanında bulunan Create Markup butonuna tıklanabilmeli ve Add Markup sayfası açılmalı
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Create Markup" butonuna tıklanır.
//* Sayfada "Create Markup" butonunun tıklanabilir olduğu kontrol edilir.
//* "http://192.168.186.88:81/markup/0/add" urlinin açıldığı doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/0/add" urlinin açıldığı doğrulanır.
//
//## TC9 - Kaydedilen tüm Markuplar Markup sayfasının en altında bulunan datagrid alanında gösterilmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda dataların geldiği doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC10 - Markup filter alanında ki Search butonuna tıklanabilmeli ve seçim yapılan alanlarla ilgili sonuçlar DataGrid alanında listelenmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Search Type alanından "XML Search" seçilir.
//* Markup Filter alanındaki Using Markup Type alanından "Fixed" seçilir.
//* Markup Filter alanındaki Using Markup Type 2. alanından "Per Person" seçilir.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda dataların geldiği doğrulanır.
//* Tabloda "Source Type" rowunda "Xml" datasının bulunduğu kontrol edilir.
//* Tabloda "Using Markup Type" rowunda "Fixed PerPerson" datasının bulunduğu kontrol edilir.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC11 - Markup Filter alanında bulunan Clear butonunun kontrolü
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Search Type alanından "XML Search" seçilir.
//* Markup Filter alanındaki Curreny alanından "USD" değeri seçilir.
//* "Client Nationality" dropdown menuden "Canada" değeri seçilir.
//* "Client Nationality" dropdown menuden "Canada" değeri seçildiği kontrol edilir.
//* "Buyer Group" dropdown menu tıklanabilir olduğu kontrol edilir.
//* "Buyer Group" dropdown menuden "remzi" değeri seçilir.
//* "Buyer" dropdown menuden "deniz tur" değeri seçilir.
//* "Buyer" dropdown menuden "ilhan" değeri seçilir.
//* Markup Filter alanındaki Accommodation Date alanında start date day "16" end date day "30" olarak seçilir.
//* Sayfada "Clear" butonuna tıklanır.
//* Markup filter alanındaki tüm filtrelerin temizlendiği doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC12 - Markupların bulunduğu data grid alanda ki paginationun kontrolü
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Search" butonuna tıklanır.
//* Data grid alanında datagrid pager olduğu doğrulanır.
//* Data grid alanında "2". sayfaya tıklanır.
//* Data grid alanında "2". sayfaya gidildiği doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC13 - Data grid toolbarda ki export excel butonuna tıklanabilmeli ve excel olarak indirme işlemi yapılabilmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Search" butonuna tıklanır.
//* Data grid alanında datagrid pager olduğu doğrulanır.
//* Data grid alanında "2". sayfaya tıklanır.
//* Data grid alanında "2". sayfaya gidildiği doğrulanır.
//* Datagrid toolbar alanında Refresh, Export Excel, Column Chooser butonlarının olduğu doğrulanır.
//* Export Excel butonuna tıklanır.
//* Excel "Markup-List" dosyasının doğru uzantıda olduğu kontrol edilir.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC14 - Data grid toolbarda ki column chooser butonuna tıklanabilmeli ve filtre seçilebilmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Datagrid alanında bulunan column chooser butonuna tıklanır.
//* Column Chooser pop-up'ında filtrelerin olduğu doğrulanır.
//* Datagrid alanında column chooser pop-up'ının açıldığı kontrol edilir.
//* Column Chooser pop-up'ında "Rolling Date" checkbox'ı işaretlenir.
//* Column Chooser pop-up'ında "Rolling Days" checkbox'ı işaretlenir.
//* Column Chooser pop-up'ında "Nightly Markup" checkbox'ı işaretlenir.
//* Column Chooser pop-up'ı kapatılır.
//* Tabloda "Rolling Date" alanının olduğu doğrulanır.
//* Tabloda "Rolling Days" alanının olduğu doğrulanır.
//* Tabloda "Nightly Markup" alanının olduğu doğrulanır.
//* Datagrid alanında bulunan column chooser butonuna tıklanır.
//* Column Chooser pop-up'ında bütün filtre checkboxlarına tıklanır ve filtre seçimlerinin kaldırılamadığı doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC15 - Datagrid alanında bulunan delete butonunun kontrolü
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda edit ve delete butonunun geldiği kontrol edilir.
//* Datagrid alanında ID "712" olan datanın delete butonuna tıklanır.
//* "http://192.168.186.88:81/markup/712/delete" urlinin açıldığı doğrulanır.
//* Sayfada "Delete" butonuna tıklanır.
//* Açılan pop-up'ta "Yes" ve "No" butonlarının bulunduğu kontrol edilir.
//* Sayfada "No" butonuna tıklanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/712/delete" urlinin açıldığı doğrulanır.
//
//## TC16 - Datagrid alanında bulunan edit butonunun kontrolü
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda edit ve delete butonunun geldiği kontrol edilir.
//* Datagrid alanında ID "712" olan datanın edit butonuna tıklanır.
//* "http://192.168.186.88:81/markup/712/edit" urlinin açıldığı doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/712/edit" urlinin açıldığı doğrulanır.
//
//## TC17 - Data grid toolbarda ki refresh butonuna tıklanabilmeli ve tüm veriler sıfırlanmalı
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Search Type alanından "XML Search" seçilir.
//* Sayfada "Search" butonuna tıklanır.
//* Column Client Nationality kolonu sürüklenip ve bırakılır.
//* Datagrid alanında bulunan column chooser butonuna tıklanır.
//* Column Chooser pop-up'ında filtrelerin olduğu doğrulanır.
//* Column Chooser pop-up'ında "Rolling Date" checkbox'ı işaretlenir.
//* Column Chooser pop-up'ında "Rolling Days" checkbox'ı işaretlenir.
//* Column Chooser pop-up'ı kapatılır.
//* Tabloda "Rolling Date" alanının olduğu doğrulanır.
//* Tabloda "Rolling Days" alanının olduğu doğrulanır.
//* Datagrid alanında bulunan refresh butonuna tıklanır.
//* Markup filter alanındaki tüm filtrelerin temizlendiği doğrulanır.
//* Search inputlarında data olmadığı kontrol edilir.
//* Dropdown inputlarında data olmadığı kontrol edilir.
//* "5" saniye bekle
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//
//// BEKLEME YAPINCA SISTEM ONCEDEN GELİYORR.......
//// SORULACAK ......
//## TC18 - Export Excel butonu ile indirilen excelin saat bilgisi doğru olmalı
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
////* "5" dakika beklenir.
//* Export Excel butonuna tıklanır.
//* Excel "Markup-List" dosyasının doğru uzantıda olduğu kontrol edilir.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC19 - Markup Filter ekranında Using Markup Type seçildikten sonra clear butonuna tıklanır. Tekrardan Using Markup Type seçimi yapılmadan Per Unit, Per Person, Per Reservation seçimleri yapılamamalı.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Markup Filter alanındaki Using Markup Type alanından "Percentage" seçilir.
//* Markup Filter alanındaki Using Markup Type 2. alanından "Per Unit" seçilir.
//* Sayfada "Clear" butonuna tıklanır.
//* Markup filter alanındaki tüm filtrelerin temizlendiği doğrulanır.
//* Using Markup Type alanında seçim yapılmadığı durumda "No Data" yazıldığı kontrol edilir.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC20 - Markup Filter ekranında ki Rolling Days inputuna veri girişi olarak "0" girildiğinde sadece ilgili sonuçlar listelenmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Rolling days alanına "0" değeri girilir.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda dataların geldiği doğrulanır.
//* Datagrid alanında bulunan column chooser butonuna tıklanır.
//* Datagrid alanında column chooser pop-up'ının açıldığı kontrol edilir.
//* Column Chooser pop-up'ında "Rolling Days" checkbox'ı işaretlenir.
//* Column Chooser pop-up'ı kapatılır.
//* Tabloda "Rolling Days" alanının olduğu doğrulanır.
//* Tabloda "Rolling Days" rowunda "" datasının bulunduğu kontrol edilir.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC21 - Edit Markup sayfası açıldığında Xml Search ve Live Search alanları pasif gelmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda edit ve delete butonunun geldiği kontrol edilir.
//* Datagrid alanında ID "711" olan datanın edit butonuna tıklanır.
//* "http://192.168.186.88:81/markup/711/edit" urlinin açıldığı doğrulanır.
//* "XML Search" ve "Live Search" seçimlerinin disabled olduğu doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/711/edit" urlinin açıldığı doğrulanır.
//
//## TC22 - Xml Search seçilerek oluşturulan bir markupın Edit Markup sayfası açıldığında tarih seçimi olarak sadece Accommodation Date Between alanı aktif olmalı
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Search Type alanından "XML Search" seçilir.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda edit ve delete butonunun geldiği kontrol edilir.
//* Datagrid alanında ID "712" olan datanın edit butonuna tıklanır.
////* Datagrid alanında Source Type "Xml" olan datanın edit butonuna tıklanır.
//* "http://192.168.186.88:81/markup/712/edit" urlinin açıldığı doğrulanır.
//* "XML Search" ve "Live Search" seçimlerinin disabled olduğu doğrulanır.
//* Sayfada "Add a Date Group" butonuna tıklanır.
//* Accommodation Date alanında start date day "20" end date day "30" olarak seçilir.
//* Check-in Date Between, Booking Date Window seçimlerinin disabled olduğu doğrulanır.
//* Rolling Date, Rolling Days seçimlerinin disabled olduğu doğrulanır.
//* Sayfada "Back" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC23 - Live search seçilerek oluşturulan bir markupın Edit Markup sayfası açıldığında birden çok yeni tarih alanı eklendiğinde bütün tarih alanları aktif gelmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Search Type alanından "Live Search" seçilir.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda edit ve delete butonunun geldiği kontrol edilir.
//* Datagrid alanında ID "711" olan datanın edit butonuna tıklanır.
////* Datagrid alanında Source Type "LiveSearch" olan datanın edit butonuna tıklanır.
//* "http://192.168.186.88:81/markup/711/edit" urlinin açıldığı doğrulanır.
//* Sayfada "Add a Date Group" butonuna tıklanır.
//* Check-in Date Between,Accommodation Date Between, Booking Date Window, Rolling Date, Rolling Days seçimlerinin active olduğu doğrulanır.
//* Accommodation Date alanında start date day "20" end date day "30" olarak seçilir.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/711/edit" urlinin açıldığı doğrulanır.
//
//## TC24 - Oluşturulan Markuplar Delete edilebilmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Create Markup" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/0/add" urlinin açıldığı doğrulanır.
//* "XML Search" search radio butonuna tıklanır.
//* "XML Search" search radio butonunun seçili olduğu kontrol edilir.
//* "Hotel Destination" alanına "Sisli, Istanbul, Turkey" değeri girilir.
//* "Hotel Name" alanına "Listana Hotel, Istanbul, Turkey" değeri girilir.
//* "Client Nationality" dropdown menuden "Canada" değeri seçilir.
//* "Channel" dropdown menusunden "B2B" değeri seçilir.
//* Markup Type alanından "Extra" seçilir.
//* Using Markup Type alanından "Percentage" seçilir.
//* Using Markup Type 2. alanından "Per Unit" seçilir.
//* Markup Value alanına "100" değeri girilir.
//* Name alanına "Nejdet Arslan" değeri girilir.
//* Expire Date alanında start date day "25" end date day "26" olarak seçilir.
//* Is Active butonu disable hale getirilir.
//* Sayfada "Save" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda edit ve delete butonunun geldiği kontrol edilir.
//* Datagrid alanında "Hotel Name" rowunda "Listana Hotel" olan datanın delete butonuna tıklanır.
//* Sayfada "Delete" butonuna tıklanır.
//* Açılan pop-up'ta "Yes" ve "No" butonlarının bulunduğu kontrol edilir.
//* Sayfada "Yes" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Sayfada "Search" butonuna tıklanır.
//* Datagrid alanında datanın silindiği doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC25 - Delete Markup sayfasında ki Delete butonuna tıklandığında alert gösterilmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Create Markup" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/0/add" urlinin açıldığı doğrulanır.
//* "Live Search" search radio butonuna tıklanır.
//* "Live Search" search radio butonunun seçili olduğu kontrol edilir.
//* "Hotel Name" alanına "Listana Hotel, Istanbul, Turkey" değeri girilir.
//* "Hotel Destination" alanına "Sisli, Istanbul, Turkey" değeri girilir.
//* "Chain Name" alanına "AC by Marriott" değeri girilir.
//* Markup Type alanından "Base" seçilir.
//* Using Markup Type alanından "Fixed" seçilir.
//* Using Markup Type 2. alanından "Per Person" seçilir.
//* Markup Value alanına "50" değeri girilir.
//* Curreny alanından "USD" değeri seçilir.
//* Name alanına "Burak Koyanoğlu" değeri girilir.
//* Expire Date alanında start date day "20" end date day "25" olarak seçilir.
//* Is Active butonu aktif olduğu kontrol edilir.
//* Sayfada "Save" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda edit ve delete butonunun geldiği kontrol edilir.
//* Datagrid alanında "Hotel Destination" rowunda "Sisli" olan datanın delete butonuna tıklanır.
//* Sayfada "Delete" butonuna tıklanır.
//* Delete pop-up içeriğinin "Are you sure to delete this markup?" olduğu doğrulanır.
//* Açılan pop-up'ta "Yes" ve "No" butonlarının bulunduğu kontrol edilir.
//* Sayfada "Yes" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Sayfada "Search" butonuna tıklanır.
//* Datagrid alanında datanın silindiği doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//
//## TC26 - Delete Markup sayfasında ki alanlar pasif olarak gelmeli
//* "http://192.168.186.88:81/markup" urlinin açıldığı doğrulanır.
//* Login sayfasında "Username" alanına "OdeonbedsAdmin" değeri girilir.
//* Login sayfasında "Password" alanına "De53Acy2.." değeri girilir.
//* Sayfada "Submit" butonuna tıklanır.
//* OdeonBeds logosunun geldiği kontrol edilir.
//* Sayfada "EN" butonuna tıklanır.
//* Sayfada "Search" butonuna tıklanır.
//* Tabloda edit ve delete butonunun geldiği kontrol edilir.
//* Datagrid alanında ID "711" olan datanın delete butonuna tıklanır.
//* "http://192.168.186.88:81/markup/711/delete" urlinin açıldığı doğrulanır.
//* Delete markup sayfasında alanların disabled olduğu doğrulanır.
//* Sayfada "Sign Out" butonuna tıklanır.
//* Sign Out pop-up'ının açıldığı kontrol edilir.
//* Açılan pop-up'ta "OK" ve "Cancel" butonlarının bulunduğu kontrol edilir.
//* Sayfada "OK" butonuna tıklanır.
//* "http://192.168.186.88:81/markup/711/delete" urlinin açıldığı doğrulanır.






