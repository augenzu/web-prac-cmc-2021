begin;
insert into good (good_type, good_name,
    good_price, good_company, good_assembly_place,
    good_quantity, good_characteristics, good_description)
values
  ('coffee_maker', 'Cuisinart DCC3200W',
  	99.95, 'Cuisinart', 'China', 33,
    '{"Width": 9.00, "Height": 14.00, "Depth": 7.75}',
    'Cuisinart DCC-3200W 14-Cup PerfecTemp Programmable Coffeemaker, in White'),
  ('cooktop', 'Professional Series 36 Inch Gas Sealed Burner Cooktop',
    249.75, 'Dacor', 'Germany', 20,
    '{"Series": "Professional", "Width": 36, "Cutout Width": 33.625,
    "Cutout Depth": 19.625, "Cutout Height": 40.450, "Control Type": "Knobs"}',
    'Dacor Professional Series 36 Inch Natural Gas Cooktop with 5 Sealed Burners, 
    Sabbath Mode, SimmerSear Burners, Perma-Flame, SmartFlame, 
    Die-Cast Knobs with Illumina Indicator Lights, 
    PermaClean Bead Blasted Finish in Stainless Steel'),
  ('fridge', 'Contemporary Series 36 Inch Stainless Steel Counter Depth French Door Refrigerator',
    3499.00, 'Fisher Paykel', 'Finnland', 4,
    '{"Series": "Contemporary", "Width": 35.625, "Height": 70.5, "Depth": 27.0625,
    "Type": "Freestanding", "Style": "French Door"}',
    'Fisher Paykel Contemporary Series 36 Inch Freestanding Counter 
    Depth 4 Door French Door Refrigerator with 19 cu. ft. Total Capacity, 4 Glass Shelves, 
    3.45 cu. ft. Freezer Capacity, External Water Dispenser, Crisper Drawer, Frost Free Defrost, 
    Ice Maker, ActiveSmart Foodcare, Ice Maker, Variable Temperature Zone in Stainless Steel'),
  ('microwave', '30 Inch Stainless Steel Over the Range 1.7 cu. ft. Capacity Microwave Oven',
    343.85, 'Whirlpool', 'Germany', 13,
    '{"Width": 29.9375, "Height": 17.125, "Depth": 15.5625, "Style": "Over the Range",
    "CFM": 300, "Cooking Watts": 1000}',
    'Whirlpool 30 Inch Over the Range Microwave Oven with 1.7 cu. ft. Capacity, 1000 Cooking Watts, 
    Convertible Venting, 300 CFM, Add 30 Seconds in Stainless Steel'),
  ('tv', 'Samsung UN50TU7000FXZA',
    399.00, 'Samsung', 'China', 57,
    '{"Type": "LED", "Width": 44, "Height": 25.4, "Depth": 2.4, "HD": "4K", "Smart TV": "Yes"}',
    'Samsung UN50TU7000FXZA 50" TU7000 Crystal UHD 4K Smart TV with Crystal Processor 4K, 
    Boundless Design and HDR in Black'),
  ('vacuum_cleaner', 'Ball Animal 2 Bagless Upright Vacuum',
    220.00, 'Dyson', 'China', 60,
    '{"Assembled Product Length": 13.4, "Assembled Product Width": 15.4, "Assembled Product Height": 42.4,
    "Amps": 11.67, "Noise Level (Decibels)": 78.1, "Vacuum Type": "UPRIGHT", "Filter Type": "HEPA",
    "Corded or Cordless": "CORDED", "Cord Length": 420, "Bagged or Bagless": "BAGLESS",
    "Bin Capacity": 1.8, "Hose Length": 184.5, "Power (Air Watts)": 270}',
    'Radial Root Cyclone(TM) technology helps remove more dirt, dust and allergens from the home 
    while the self-adjusting cleaner head automatically adjusts to seal in suction, even on hard floors. 
    Dyson''s reconfigured bristles dig deeper into carpets to remove more dirt. 
    But what makes this vacuum really unique is the Ball(TM) technology. Core components are inside the ball, 
    creating a lower centre of gravity allowing you to steer easily into difficult places. 
    On a different level, an instant release wand helps you clean up high and down low, under furniture.'),
  ('washer', '4.5 cu. ft. 27 Inch Front Load Washer', 126.75, 'LG', 'China', 45,
    '{"Width": 27, "Height": 39, "Depth": 30.25, "Type": "Front",
    "LoadCapacity": "4.5 cu. ft.", "Wash Cycles": 12}',
    'LG 27 Inch Smart Front Load Washer with 4.5 cu. ft. Capacity, 
    Wi-Fi Enabled, 12 Wash Cycles, 1300 RPM, Steam Cycle, 
    TurboWash, WiFi Connect, ColdWash, TrueBalance, NeveRust Stainless Steel Drum, 
    Child Lock, SmartThinQ Works with Google Assistant/Amazon Alexa, 
    10 Year Warranty on Direct Drive Motor, Allergiene Cycle, SenseClean System in Black Steel');
commit;

begin;
insert into "user" (user_name, user_address, user_number, user_email)
values
  ('Marcia J. Wardle', '1681 Goldcliff Circle, Washington, DC 20032', '1-202-561-9265', 'MarciaJWardle@armyspy.com'),
  ('Laura M. McKnight', '947 Jerome Avenue, Harlingen, TX 78550dddddd', '1-956-263-6424', 'LauraMMcKnight@dayrep.com '),
  ('Richard M. Kirk', '3080 Heather Sees Way, Sallisaw, OK 74955', '1-918-776-2134', 'RichardMKirk@rhyta.com'),
  ('Gary L. Graves', '9 Petworth Rd, DUNVEGAN, IV55 9SZ', '44-079-6565-5402', 'GaryLGraves@armyspy.com'),
  ('Treasa R. Fields', '40 Jesmond Rd, KILHAM, YO25 9EG', '44-077-8481-9728', 'TreasaRFields@jourrapide.com'),
  ('Maria T. Cummings', '276 Oak Ridge Drive, Mexico, MO 65265', '1-573-581-6184', 'MariaTCummings@dayrep.com'),
  ('Jay K. Ellison', '1277 Columbia Road, Philadelphia, DE 19108', '1-302-780-3367', 'JayKEllison@teleworm.us'),
  ('Cleveland S. Stokes', '3481 Davis Avenue, Dublin, CA 94568', '1-707-756-5687', 'ClevelandSStokes@teleworm.us'),
  ('Raquel T. Sabin', '1 Fairmont Avenue, Kansas City, MO 64106', '1-660-274-0008', 'RaquelTSabin@jourrapide.com');
commit;

begin;
insert into "order" (user_id, order_time, order_status, order_delivery_address, order_delivery_date)
values
  (2, '2021-01-03 16:57', 'delivered', '947 Jerome Avenue, Harlingen, TX 78550dddddd', '2021-02-02'),
  (2, '2021-02-10 16:08', 'processing', '947 Jerome Avenue, Harlingen, TX 78550dddddd', null),
  (3, '2021-02-10 23:54', 'processing', '3080 Heather Sees Way, Sallisaw, OK 74955', null),
  (5, '2020-12-29 08:03', 'delivered', '40 Jesmond Rd, KILHAM, YO25 9EG', '2021-01-03'),
  (6, '2021-01-23 15:30', 'complete', '276 Oak Ridge Drive, Mexico, MO 65265', '2021-03-01'),
  (9, '2021-02-01 03:13', 'processing', '1 Fairmont Avenue, Kansas City, MO 64106', null);
commit;

begin;
insert into order_good (order_id, good_id, order_good_quantity)
values
  (1, 1, 3),
  (1, 4, 1),
  (1, 6, 1),
  (2, 3, 1),
  (3, 3, 1),
  (4, 1, 5),
  (4, 2, 1),
  (4, 7, 2),
  (5, 1, 2),
  (5, 5, 3),
  (6, 1, 1),
  (6, 2, 1);
commit;
