--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animals; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE animals (
    id integer NOT NULL,
    animalname character varying,
    photo character varying,
    type integer
);


ALTER TABLE animals OWNER TO "Guest";

--
-- Name: animals_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animals_id_seq OWNER TO "Guest";

--
-- Name: animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE animals_id_seq OWNED BY animals.id;


--
-- Name: locations; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE locations (
    id integer NOT NULL,
    description character varying,
    maprow integer,
    mapcol integer
);


ALTER TABLE locations OWNER TO "Guest";

--
-- Name: locations_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE locations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE locations_id_seq OWNER TO "Guest";

--
-- Name: locations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE locations_id_seq OWNED BY locations.id;


--
-- Name: people; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE people (
    id integer NOT NULL,
    firstname character varying,
    lastname character varying,
    phonenumber character varying,
    address character varying,
    city character varying,
    state character varying,
    zip character varying,
    email character varying,
    badge integer,
    workcontact character varying,
    trainername character varying,
    level integer,
    type integer
);


ALTER TABLE people OWNER TO "Guest";

--
-- Name: people_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE people_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE people_id_seq OWNER TO "Guest";

--
-- Name: people_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE people_id_seq OWNED BY people.id;


--
-- Name: sightings; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE sightings (
    id integer NOT NULL,
    animalid integer,
    personid integer,
    locationid integer,
    "time" timestamp without time zone,
    age integer,
    health integer,
    cp integer
);


ALTER TABLE sightings OWNER TO "Guest";

--
-- Name: sightings_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE sightings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sightings_id_seq OWNER TO "Guest";

--
-- Name: sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE sightings_id_seq OWNED BY sightings.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY animals ALTER COLUMN id SET DEFAULT nextval('animals_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY locations ALTER COLUMN id SET DEFAULT nextval('locations_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY people ALTER COLUMN id SET DEFAULT nextval('people_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY sightings ALTER COLUMN id SET DEFAULT nextval('sightings_id_seq'::regclass);


--
-- Data for Name: animals; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY animals (id, animalname, photo, type) FROM stdin;
1	Opossum (Didelphis virginiana)	006_opossum_nwwd_odfw.jpg	1
2	Mountain Beaver (Aplodontia rufa)	mountain_beaver.jpg	1
3	American Beaver (Castor canadensis)	beaver.jpg	1
4	Common Porcupine (Erethizon dorsatum)	porcupine.jpg	1
5	Nutria (Myocastor Coypus)	Nutria.jpg	1
6	Black Bear (Ursus americanus)	black_bear_walking_odfw.jpg	1
7	Cougar (Puma concolor)	cougar.jpg	1
8	Bobcat (Lynx rufus)	bobcat_odfw.jpg	1
9	Coyote (Canis latrans)	coyote_yip.jpg	1
10	Common Gray Fox (Urocyon cinereoargenteus)	greyfox_odfw.jpg	1
11	Kit Fox (Vulpes velox)	kit_fox_wikipedia.jpg	1
12	Red Fox (Vulpes vulpes)	red_fox_odfw.jpg	1
13	Gray Wold (Canis lupus)	gray_wolf_USFWS.jpg	2
14	Canada Lynx (Lynx canadensis)	canada_lynx_usfws.jpg	2
15	Columbian White-tailed Deer (Odocoileus virginianus leucurus)	CWTD-2FWS.jpg	2
16	Marbled Murrelet (Brachyramphus marmoratus)	MarbledMurreletFWS.jpg	2
17	Western Snowy Plover (Charadrius alexandrinus nivosus)	WSnowyPloverMikeBairdCC.jpg	2
18	Yellow-billed Cuckoo *Coccyzua americanus)	YBC-Wikipedia.jpg	2
19	Streaked Horned Lark (Eremophila alphestris stigata)	STHL-RodGilbert.jpg	2
20	Short-tailed Albatross Phoebastria albatrus)	Short-tailed-albatross.jpg	2
21	Nothern Spotted Owl (Strix occidentalis caurina)	northern_spotted_owl_family_USFWS.jpg	2
22	Sea Turtle	GreenTurtleRPVanDam.jpg	2
23	Oregon Spotted Frog (Rana pretiosa)	OR.SpottedFrogGaryNafisCalHerps.jpg	2
24	Vernal Pool Fairy Shrimp (Branchinecta lynchi)	VernalPoolFairyShrimpFWS.jpg	2
25	Taylor's Checkerspot Butterfly (Euphydryas editha taylor)	TaylorsCheckerspot.FWS.jpg	2
26	Fender's Blue Butterfly (Icaricia icarioides fenderi)	FendersBlueFWS.jpg	2
27	Oregon Silverspot Butterfly (Speyeria zerene hippolyta)	OregonSilverspotFWS.jpg	2
92	Alakazam	pokemon/065.png	3
51	Arbok	pokemon/024.png	3
86	Arcanine	pokemon/059.png	3
42	Beedrill	pokemon/015.png	3
96	Bellsprout	pokemon/069.png	3
36	Blastoise	pokemon/009.png	3
28	Bulbasaur	pokemon/001.png	3
39	Butterfree	pokemon/012.png	3
37	Caterprie	pokemon/010.png	3
31	Charmander	pokemon/004.png	3
32	Charmeleon	pokemon/005.png	3
63	Clefable	pokemon/036.png	3
62	Clefairy	pokemon/035.png	3
77	Diglett	pokemon/050.png	3
112	Dodrio	pokemon/085.png	3
111	Doduo	pokemon/084.png	3
78	Dugtrio	pokemon/051.png	3
50	Ekans	pokemon/023.png	3
110	Farfetch'd	pokemon/083.png	3
49	Fearow	pokemon/022.png	3
101	Geodude	pokemon/074.png	3
71	Gloom	pokemon/044.png	3
69	Golbat	pokemon/042.png	3
82	Golduck	pokemon/055.png	3
103	Golem	pokemon/076.png	3
85	Growlithe	pokemon/058.png	3
29	Ivysaur	pokemon/002.png	3
66	Jigglypuff	pokemon/039.png	3
91	Kadabra	pokemon/064.png	3
41	Kakuna	pokemon/013.png	3
95	Machamp	pokemon/068.png	3
94	Machoke	pokemon/067.png	3
93	Machop	pokemon/066.png	3
108	Magnemite	pokemon/081.png	3
109	Magneton	pokemon/082.png	3
83	Mankey	pokemon/056.png	3
79	Meowth	pokemon/052.png	3
38	Metapod	pokemon/011.png	3
61	Nidoking	pokemon/034.png	3
58	Nidoqueen	pokemon/031.png	3
56	Nidoran	pokemon/029.png	3
59	Nidoran	pokemon/032.png	3
60	Nidorino	pokemon/033.png	3
65	Ninetales	pokemon/038.png	3
70	Oddish	pokemon/043.png	3
73	Paras	pokemon/046.png	3
74	Parasect	pokemon/047.png	3
80	Persian	pokemon/053.png	3
45	Pidgeot	pokemon/018.png	3
43	Pidgey	pokemon/016.png	3
44	Pigeotto	pokemon/017.png	3
52	Pikachu	pokemon/025.png	3
87	Poliwag	pokemon/060.png	3
88	Poliwhirl	pokemon/61.png	3
89	Poliwrath	pokemon/062.png	3
104	Ponyta	pokemon/077.png	3
84	Primeape	pokemon/057.png	3
81	Psyduck	pokemon/054.png	3
53	Raichu	pokemon/026.png	3
47	Raticate	pokemon/020.png	3
46	Rattata	pokemon/019.png	3
54	Sandshrew	pokemon/027.png	3
55	Sandslash	pokemon/028.png	3
107	Slowbro	pokemon/080.png	3
106	Slowpoke	pokemon/079.png	3
48	Spearow	pokemon/021.png	3
34	Squirtle	pokemon/007.png	3
99	Tentacool	pokemon/072.png	3
100	Tentacruel	pokemon/073.png	3
76	Venomoth	pokemon/049.png	3
75	Venonat	pokemon/048.png	3
30	Venusaur	pokemon/003.png	3
98	Victreebel	pokemon/071.png	3
64	Vulpix	pokemon/037.png	3
35	Wartortle	pokemon/008.png	3
40	Weedle	pokemon/013.png	3
97	Weepinbell	pokemon/070.png	3
67	Wigglytuff	pokemon/040.png	3
68	Zubat	pokemon/041.png	3
90	Abra	pokemon/063.png	3
120	Haunter	pokemon/093.png	3
171	Articuno	pokemon/144.png	3
134	Hitmonchan	pokemon/107.png	3
140	Chansey	pokemon/113.png	3
33	Charizard	pokemon/006.png	3
165	Omanyte	pokemon/138.png	3
118	Cloyster	pokemon/091.png	3
133	Hitmonlee	pokemon/106.png	3
131	Cubone	pokemon/104.png	3
169	Aerodactyl	pokemon/142.png	3
114	Dewgong	pokemon/087.png	3
143	Horsea	pokemon/116.png	3
159	Ditto	pokemon/132.png	3
166	Omastar	pokemon/139.png	3
175	Dragonair	pokemon/148.png	3
124	Hypno	pokemon/097.png	3
176	Dragonite	pokemon/149.png	3
174	Dratini	pokemon/147.png	3
162	Jolteon	pokemon/135.png	3
123	Drowzee	pokemon/096.png	3
122	Onix	pokemon/095.png	3
160	Eevee	pokemon/133.png	3
151	Jynx	pokemon/124.png	3
152	Electabuzz	pokemon/125.png	3
128	Electrode	pokemon/101.png	3
167	Kabuto	pokemon/140.png	3
129	Exeggcute	pokemon/102.png	3
154	Pinsir	pokemon/127.png	3
130	Exeggutor	pokemon/103.png	3
168	Kabutops	pokemon/141.png	3
163	Flareon	pokemon/136.png	3
119	Gastly	pokemon/092.png	3
142	Kangaskhan	pokemon/115.png	3
121	Gengar	pokemon/094.png	3
164	Porygon	pokemon/137.png	3
145	Goldeen	pokemon/118.png	3
102	Graveler	pokemon/075.png	3
126	Kingler	pokemon/099.png	3
115	Grimer	pokemon/088.png	3
157	Gyarados	pokemon/130.png	3
105	Rapidash	pokemon/078.png	3
136	Koffing	pokemon/109.png	3
125	Krabby	pokemon/098.png	3
139	Rhydon	pokemon/112.png	3
158	Lapras	pokemon/131.png	3
135	Likitung	pokemon/108.png	3
138	Rhyhorn	pokemon/111.png	3
156	Magikarp	pokemon/129.png	3
153	Magmar	pokemon/126.png	3
144	Seadra	pokemon/117.png	3
132	Marowak	pokemon/105.png	3
178	Mew	pokemon/151.png	3
146	Seaking	pokemon/119.png	3
177	Mewtwo	pokemon/150.png	3
173	Moltres	pokemon/146.png	3
113	Seel	pokemon/086.png	3
149	Mr. Mime	pokemon/122.png	3
116	Muk	pokemon/089.png	3
57	Nidorina	pokemon/030.png	3
117	Shellder	pokemon/090.png	3
170	Snorlax	pokemon/143.png	3
148	Starmie	pokemon/121.png	3
147	Staryu	pokemon/120.png	3
150	Syther	pokemon/123.png	3
141	Tangela	pokemon/114.png	3
155	Tauros	pokemon/128.png	3
161	Vaporeon	pokemon/134.png	3
72	Vileplume	pokemon/045.png	3
127	Voltorb	pokemon/100.png	3
137	Weezing	pokemon/110.png	3
172	Zapdos	pokemon/145.png	3
\.


--
-- Name: animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('animals_id_seq', 178, true);


--
-- Data for Name: locations; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY locations (id, description, maprow, mapcol) FROM stdin;
\.


--
-- Name: locations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('locations_id_seq', 1, false);


--
-- Data for Name: people; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY people (id, firstname, lastname, phonenumber, address, city, state, zip, email, badge, workcontact, trainername, level, type) FROM stdin;
\.


--
-- Name: people_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('people_id_seq', 1, false);


--
-- Data for Name: sightings; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY sightings (id, animalid, personid, locationid, "time", age, health, cp) FROM stdin;
\.


--
-- Name: sightings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('sightings_id_seq', 1, false);


--
-- Name: animals_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- Name: locations_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id);


--
-- Name: people_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY people
    ADD CONSTRAINT people_pkey PRIMARY KEY (id);


--
-- Name: sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY sightings
    ADD CONSTRAINT sightings_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

