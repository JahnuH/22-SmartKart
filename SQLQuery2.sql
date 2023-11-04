create table retail_detail(
BARCODE_NUMBER nchar(50),
ITEM_NAME varchar(50),
ITEM_UNIT_SIZE int,
ITEM_PRICE_PER_UNIT int,
ITEM_CATEGORY varchar(50),
ITEM_BRAND varchar(50),
CALORIES nchar(100),
ALLERGEN varchar(100),
);

insert into retail_detail
values
('8901491503051','Lays- Hot and Sweet',2,10,'Snacks','Lays',159,'Barley and Sulphite'),
('8906010750114','Premier-Paper Handkerchief',1,10,'Toiletries','Premier','-','NA'),		
('8902080002023','Tropicana- Mixed Fruit Delight',2,20,'Drink','Tropicana',52,'Fruits'),
('8906065450175','Gone Mad- Choco Stick',4,5,'Snacks','Garuda Foods',492,'Milk,Wheat and Soya'),	
('8902080002092','Tropicana- Mango Delight',2,20,'Drink','Tropicana',120,'Mango'),	
('8904256018180','NIVIA deodorant- pearl & beauty',1,99,'Beauty','Nivia','-','NA'),
('8901491503020','Lays- Indias Magic Masala',2,10,'Snacks','Lays',159	,'Nut,Milk,Wheat and Sulphite'),
('1002671960','Face Towel Ultra Soft',1,25,'Toiletries','D-Homes','-','NA'),
('8908006727421','Bisleri-Mineral Water',4,10,'Drink','Bisleri',0,'NA'),
('806360680506','Lotus Herbal- Sunscreen',1,315,'Beauty','Lotus','-','NA'),	
('8901030756054','Vaseline- Cocoa Butter',1,80,'Beauty','Vaseline','-','NA'),			
('8901198125600','Luxor- Highlighter Pastel',1,125,'Stationary','Luxor','-','NA');																				

select * from retail_detail;
