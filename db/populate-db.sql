begin;
insert into app_type (app_type_name)
values
  ('coffee_maker'),
  ('cooktop'),
  ('fridge'),
  ('microwave'),
  ('tv'),
  ('vacuum_cleaner'),
  ('washer');
commit;

begin;
insert into "status" (status_name)
values
  ('complete'),
  ('delivered'),
  ('processing');
commit;

begin;
insert into good (app_type_id, good_name,
    good_price, good_company, good_assembly_place,
    good_quantity, good_description)
values
  (1, 'Cuisinart DCC3200W',
  	99.95, 'Cuisinart', 'China', 33,
    'Cuisinart DCC-3200W 14-Cup PerfecTemp Programmable Coffeemaker, in White'),
  (2, 'Professional Series 36 Inch Gas Sealed Burner Cooktop',
    249.75, 'Dacor', 'Germany', 20,
    'Dacor Professional Series 36 Inch Natural Gas Cooktop with 5 Sealed Burners, 
    Sabbath Mode, SimmerSear Burners, Perma-Flame, SmartFlame, 
    Die-Cast Knobs with Illumina Indicator Lights, 
    PermaClean Bead Blasted Finish in Stainless Steel'),
  (3, 'Contemporary Series 36 Inch Stainless Steel Counter Depth French Door Refrigerator',
    3499.00, 'Fisher Paykel', 'Finnland', 4,
    'Fisher Paykel Contemporary Series 36 Inch Freestanding Counter 
    Depth 4 Door French Door Refrigerator with 19 cu. ft. Total Capacity, 4 Glass Shelves, 
    3.45 cu. ft. Freezer Capacity, External Water Dispenser, Crisper Drawer, Frost Free Defrost, 
    Ice Maker, ActiveSmart Foodcare, Ice Maker, Variable Temperature Zone in Stainless Steel'),
  (4, '30 Inch Stainless Steel Over the Range 1.7 cu. ft. Capacity Microwave Oven',
    343.85, 'Whirlpool', 'Germany', 13,
    'Whirlpool 30 Inch Over the Range Microwave Oven with 1.7 cu. ft. Capacity, 1000 Cooking Watts, 
    Convertible Venting, 300 CFM, Add 30 Seconds in Stainless Steel'),
  (5, 'Samsung UN50TU7000FXZA',
    399.00, 'Samsung', 'China', 57,
    'Samsung UN50TU7000FXZA 50" TU7000 Crystal UHD 4K Smart TV with Crystal Processor 4K, 
    Boundless Design and HDR in Black'),
  (6, 'Ball Animal 2 Bagless Upright Vacuum',
    220.00, 'Dyson', 'China', 60,
    'Radial Root Cyclone(TM) technology helps remove more dirt, dust and allergens from the home 
    while the self-adjusting cleaner head automatically adjusts to seal in suction, even on hard floors. 
    Dyson''s reconfigured bristles dig deeper into carpets to remove more dirt. 
    But what makes this vacuum really unique is the Ball(TM) technology. Core components are inside the ball, 
    creating a lower centre of gravity allowing you to steer easily into difficult places. 
    On a different level, an instant release wand helps you clean up high and down low, under furniture.'),
  (7, '4.5 cu. ft. 27 Inch Front Load Washer', 126.75, 'LG', 'China', 45,
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
insert into "order" (user_id, order_time, status_id, order_delivery_address, order_delivery_date)
values
  (2, '2021-01-03 16:57', 2, '947 Jerome Avenue, Harlingen, TX 78550dddddd', '2021-02-02'),
  (2, '2021-02-10 16:08', 3, '947 Jerome Avenue, Harlingen, TX 78550dddddd', null),
  (3, '2021-02-10 23:54', 3, '3080 Heather Sees Way, Sallisaw, OK 74955', null),
  (5, '2020-12-29 08:03', 2, '40 Jesmond Rd, KILHAM, YO25 9EG', '2021-01-03'),
  (6, '2021-01-23 15:30', 1, '276 Oak Ridge Drive, Mexico, MO 65265', '2021-03-01'),
  (9, '2021-02-01 03:13', 3, '1 Fairmont Avenue, Kansas City, MO 64106', null);
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
