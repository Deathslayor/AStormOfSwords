package net.darkflameproduction.agotmod.entity.custom.npc.system;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;

public class NameSystem {
    private final Peasant_Entity peasant;

    private static final String[] FIRST_NAMES = {
            "Alastair", "Angus", "Arran", "Blair", "Brody", "Callum", "Campbell", "Cameron", "Cormag", "Craig",
            "Dougal", "Duncan", "Euan", "Ewan", "Fergus", "Finlay", "Fraser", "Gavin", "Gordon", "Graeme",
            "Gregor", "Hamish", "Harris", "Hugh", "Iain", "Innes", "Jamie", "Keith", "Kenneth", "Kerr",
            "Knox", "Lachlan", "Lennox", "Leslie", "Logan", "Malcolm", "Murray", "Neil", "Niall", "Ramsay",
            "Robbie", "Ross", "Ruaridh", "Seamus", "Sholto", "Struan", "Tavish", "Torin", "Wallace", "Watt",
            "Alec", "Archibald", "Baird", "Brodie", "Bruce", "Calum", "Conall", "Dalziel", "Donald", "Donnie",
            "Drummond", "Duff", "Eachann", "Erskine", "Farquhar", "Farquharson", "Findlay", "Gilchrist", "Gillespie", "Grant",
            "Hector", "Ivor", "Jock", "John", "Kennan", "Kyle", "Leith", "Lorne", "Magnus", "Mathew",
            "Maxwell", "Menzies", "Munro", "Murdo", "Nairn", "Paton", "Quinn", "Ranald", "Roderick", "Ronald",
            "Roy", "Shaw", "Sloan", "Stewart", "Tam", "Taran", "Torquil", "Uisdean", "Watson", "Wilson",
            "Eddard", "Robb", "Brandon", "Rickon", "Benjen", "Torrhen", "Cregan", "Rickard", "Harrion", "Arnolf",
            "Karlon", "Roose", "Ramsay", "Domeric", "Galbart", "Robett", "Mors", "Hother", "Jon", "Jeor", "Jorah",
            "Rodrik", "Osric", "Dorren", "Theon", "Wyman", "Cley", "Howland", "Halys", "Leobald", "Donnel",
            "Robin", "Ludd", "Gryff", "Gregor", "Asher", "Ethan", "Arthur", "Eddison", "Vayon", "Wendel",
            "Jonos", "Jory", "Bowen", "Richard", "Dickon", "Roger",
            "Alebelly", "Alyn", "Bandy", "Bannen", "Barth", "Bartimus", "Bartogon", "Belthasar", "Ben", "Benfred",
            "Benjicot", "Bennard", "Benton", "Beron", "Beren", "Bran", "Cayn", "Calon", "Chayle", "Crowl",
            "Damon", "Desmond", "Donnis", "Daryn", "Edderion", "Edric", "Edrick", "Edwyle", "Edwyn", "Ellard",
            "Elric", "Errold", "Eyron", "Farlen", "Gage", "Gariss", "Gareth", "Garth", "Gawen", "Gaven",
            "Grunt", "Hallis", "Harwin", "Hayhead", "Heward", "Hodor", "Hoarfrost", "Hooded", "Hullen", "Jack",
            "Jocelyn", "Jonnel", "Jojen", "Joseth", "Larence", "Lonnel", "Lew", "Mark", "Marlon", "Maynard",
            "Medger", "Medrick", "Mikken", "Morgan", "Murch", "Nage", "Ned", "Ondrew", "Owen", "Porther",
            "Poxy", "Quent", "Rat", "Reek", "Robard", "Robyn", "Rodwell", "Rogar", "Ronnel", "Royce",
            "Shadd", "Sherrit", "Skinner", "Skittrick", "Slate", "Steelshanks", "Theodan", "Theo", "Therry", "Timotty",
            "Tomard", "TomToo", "Torghen", "Torren", "Turnip", "Varly", "Warrick", "Wayn", "Will", "Willam",
            "Wyl", "Wylis", "Wynton", "Yellow"
    };

    private static final String[] CHILD_MALE_FIRST_NAMES = {
            // Northern English/Game of Thrones male child names
            "Alyn", "Artos", "Bran", "Ben", "Benjen", "Brandon", "Collin", "Dickon", "Duncan", "Edric",
            "Eddard", "Elric", "Gareth", "Gendry", "Harwin", "Hodor", "Joffrey", "Jon", "Jojen", "Jory",
            "Lommy", "Luwin", "Maester", "Mikken", "Ned", "Olyvar", "Owen", "Podrick", "Rickon", "Robb",
            "Robin", "Rodrik", "Samwell", "Tommen", "Waymar", "Will", "Willem", "Wyl",

            // Additional Northern child names
            "Aedan", "Aldric", "Ansel", "Aron", "Baelor", "Bennet", "Beric", "Brynden", "Calon", "Cley",
            "Daemon", "Dareon", "Desmond", "Donal", "Donnel", "Drogo", "Edwyn", "Eldon", "Ellard", "Elyas",
            "Emmon", "Ethan", "Franklyn", "Garse", "Garrett", "Gorold", "Gulian", "Harys", "Helman", "Hobber",
            "Hoster", "Humfrey", "Janos", "Jaremy", "Jasper", "Jeffory", "Jeren", "Kevan", "Lancel", "Loras",
            "Lucas", "Lyonel", "Marq", "Martyn", "Meryn", "Myles", "Norbert", "Osmund", "Perwyn", "Quentyn",
            "Renly", "Rhaegar", "Rickard", "Roderick", "Ronnel", "Royland", "Ryon", "Steffon", "Stevron", "Terrance",
            "Theon", "Theo", "Torrhen", "Tristan", "Tytos", "Ulmer", "Viserys", "Walder", "Walton", "Wendel"
    };

    private static final String[] CHILD_FEMALE_FIRST_NAMES = {
            // Northern English/Game of Thrones female child names
            "Arya", "Bessa", "Beth", "Bran", "Cat", "Cersei", "Daisy", "Dany", "Elia", "Gilly",
            "Joy", "Lyanna", "Mya", "Myrcella", "Nan", "Nymeria", "Penny", "Roslin", "Sansa", "Shireen",
            "Talla", "Ygritte", "Ysilla",

            // Additional Northern child names
            "Alla", "Alarra", "Alerie", "Alys", "Amerei", "Arwyn", "Ashara", "Betha", "Brienne", "Catelyn",
            "Delena", "Denyse", "Dyanna", "Elinor", "Ellyn", "Ermesande", "Falena", "Genna", "Gwyneth", "Hanna",
            "Helena", "Janei", "Jayne", "Jocelyn", "Karla", "Lanna", "Leyla", "Lollys", "Lynesse", "Malora",
            "Mariah", "Marissa", "Meredyth", "Minisa", "Morya", "Mylenda", "Naerys", "Pia", "Rhaella", "Rhonda",
            "Roslyn", "Selyse", "Sybelle", "Taena", "Tanda", "Tya", "Tyanna", "Victaria", "Walda", "Wynafrei",

            // Cute/diminutive versions
            "Ally", "Annie", "Barbie", "Betsy", "Bonnie", "Cate", "Dolly", "Effie", "Ellie", "Emmy",
            "Faye", "Georgie", "Gracie", "Hattie", "Izzy", "Josie", "Katie", "Lettie", "Maggie", "Millie",
            "Molly", "Nettie", "Ollie", "Polly", "Rosie", "Sally", "Susie", "Tilly", "Winnie", "Zoe"
    };
    private static final String[] FEMALE_FIRST_NAMES = {
            // Northern English/Game of Thrones female names
            "Alys", "Alarra", "Alysane", "Alysanne", "Arra", "Aregelle", "Arrana", "Arsa", "Arya",
            "Barba", "Barbrey", "Bessa", "Beth", "Bethany", "Dacey", "Danny", "Donella", "Erena",
            "Gilliane", "Jeyne", "Jessamyn", "Jez", "Jonelle", "Jorelle", "Jyana", "Kyra", "Lyanne",
            "Lysara", "Lysa", "Lyanna", "Lyessa", "Lyra", "Maege", "Maisie", "Margaret", "Mara",
            "Marna", "Meera", "Meliana", "Myriame", "Palla", "Sara", "Sybelle", "Wylla", "Wynafryd",

            // Additional Northern English female names
            "Aelred", "Aelswith", "Ailith", "Aldith", "Alviva", "Annis", "Avice", "Beatrix", "Bertha",
            "Blanche", "Branwen", "Brenna", "Brigid", "Bryony", "Celia", "Cerys", "Clarice", "Constance",
            "Cordelia", "Cressida", "Deirdre", "Delwyn", "Dervla", "Dorcas", "Edith", "Edwina", "Elinor",
            "Elspeth", "Emmeline", "Enid", "Etheldreda", "Euphemia", "Evangeline", "Felicity", "Fenella",
            "Fiona", "Freya", "Garnet", "Georgiana", "Gilda", "Giselle", "Godiva", "Greta", "Gwendolyn",
            "Gwyneth", "Hazel", "Heather", "Helene", "Hilda", "Honor", "Imogen", "Iona", "Iris", "Isabel",
            "Isolde", "Ivy", "Jacinta", "Jade", "Jemima", "Jocelyn", "Judith", "Juliana", "Kendra", "Kerensa",
            "Lavinia", "Leona", "Lettice", "Lillian", "Linnet", "Lorelei", "Lucinda", "Lydia", "Mabel",
            "Madeline", "Marigold", "Marlowe", "Mathilda", "Maude", "Maxine", "Mercy", "Millicent", "Miranda",
            "Moira", "Morgana", "Morwenna", "Myfanwy", "Nerys", "Niamh", "Nicola", "Nora", "Octavia",
            "Olive", "Ophelia", "Orla", "Pandora", "Patience", "Penelope", "Perdita", "Petra", "Philippa",
            "Poppy", "Portia", "Primrose", "Prudence", "Raven", "Rhiannon", "Rosalind", "Rowena", "Ruby",
            "Sabrina", "Seraphina", "Serena", "Seren", "Siobhan", "Sorcha", "Stella", "Tabitha", "Tamsin",
            "Tessa", "Theodora", "Thomasina", "Thora", "Tilda", "Tullia", "Una", "Ursula", "Valeria",
            "Venetia", "Vera", "Veronica", "Victoria", "Violet", "Vivienne", "Willa", "Willow", "Winifred",
            "Xara", "Yolanda", "Yvette", "Yvonne", "Zelda", "Zephyr", "Zinnia", "Zora",

            // Additional Northern/Celtic variants
            "Aileas", "Aileen", "Aine", "Bronwen", "Caoimhe", "Catelyn", "Ciara", "Davina", "Delyth",
            "Effie", "Eilidh", "Eirlys", "Eleri", "Elen", "Ffion", "Gladys", "Gwen", "Gwenllian",
            "Hafwen", "Heledd", "Hestia", "Idris", "Ifan", "Iona", "Ishbel", "Kenna", "Kirsty",
            "Lorna", "Mairi", "Megan", "Morag", "Morven", "Moyra", "Muirenn", "Nessa", "Nesta",
            "Olwen", "Paisley", "Peigi", "Rhona", "Shona", "Sine", "Skye", "Sorcha", "Tegan",
            "Thea", "Ulrika", "Vaila", "Vivian", "Wenna", "Ysabel", "Zara"
    };

    private static final String[] LAST_NAMES = {
            "Ainsley", "Airey", "Archer", "Askew", "Atkinson", "Barker", "Barraclough", "Baxter", "Beadle", "Beckett",
            "Bell", "Benson", "Birkett", "Blackburne", "Blake", "Blenkinsop", "Booth", "Bramley", "Briggs", "Brock",
            "Brown", "Browne", "Bullock", "Butterworth", "Byers", "Calvert", "Carr", "Carter", "Charlton", "Clarke",
            "Clayton", "Close", "Cocker", "Collier", "Cook", "Cooper", "Corbett", "Coulson", "Cowell", "Cowley",
            "Craggs", "Craven", "Crompton", "Crook", "Cross", "Curry", "Dalton", "Dawson", "Dent", "Devine",
            "Dickinson", "Dobson", "Dodds", "Donaldson", "Dunn", "Dyson", "Eddison", "Edgar", "Edson", "Elliott",
            "English", "Etherington", "Fairhurst", "Fawcett", "Ferguson", "Fielding", "Firth", "Fleming", "Forster", "Fox",
            "Frain", "Fraser", "Gibson", "Gill", "Godfrey", "Goodwin", "Graves", "Greenwood", "Gregson", "Grey",
            "Grice", "Griffin", "Hall", "Hampson", "Handley", "Hardman", "Hargreaves", "Harker", "Harrison", "Hartley",
            "Hawkins", "Hayes", "Hewitt", "Hodgson", "Holden", "Hollis", "Holmes", "Hooper", "Hope", "Horner",
            "Houghton", "Howard", "Hudson", "Hughes", "Humphrey", "Hurst", "Ingham", "Jackson", "Jagger", "Jennings",
            "Johnston", "Jordan", "Kay", "Kendrick", "Kenyon", "Kerr", "Knowles", "Lamb", "Lang", "Layton",
            "Leach", "Lloyd", "Lofthouse", "Lord", "Lowes", "Lunn", "Maddison", "Maguire", "Marsden", "Martin",
            "Mason", "Milburn", "Milner", "Moore", "Moss", "Muir", "Murray", "Naylor", "Nelson", "Neville",
            "Nicholson", "Nixon", "Norton", "Ogden", "Oldham", "Orton", "Osborne", "Parkin", "Parr", "Pattinson",
            "Ainsworth", "Brewster", "Chapman", "Clark", "Clerk", "Draper", "Fletcher", "Forester", "Gardiner", "Gardner", "Glover", "Goldsmith", "Granger", "Greensmith", "Groom", "Groome", "Grover",
            "Harper", "Hawker", "Hawking", "Heward", "Hodgson", "Joiner", "Leathersmith", "Marshall", "Marshman", "Mercer", "Miller", "Palmer", "Parker", "Parsons", "Porter", "Procter", "Proctor", "Poulter", "Punter", "Purser",
            "Ranger", "Rector", "Reeve", "Roper", "Ropes",
            "Sexton", "Sadler", "Salter", "Sargeant", "Sargent", "Sawyer", "Sergeant", "Shaver", "Shearer", "Shepherd", "Sherman", "Shipp",
            "Shoemaker", "Shoesmith", "Shriver", "Sixsmith", "Skinner", "Slater", "Smith", "Smithe", "Smyth", "Smythe", "Soper",
            "Spencer", "Spicer", "Spindler", "Steward", "Stoddard", "Stringer",
            "Tanner", "Taverner", "Taylor", "Thacker", "Thatcher", "Threadgold", "Tinker", "Topper", "Trapp", "Truss", "Tucker", "Turner", "Tyler",
            "Usher",
            "Wainwright", "Walker", "Waller", "Ward", "Warden", "Warner", "Watson", "Weaver", "Webber", "Webster", "Wheeler", "Wheelwright", "Wilkinson", "Wright",
            "Workman", "Yeoman", "Yeats", "Young",
            "Bowyer", "Farrier",
            "Snow", "Snow", "Snow", "Snow", "Snow", "Snow", "Snow", "Snow", "Snow", "Snow", "Snow"
    };

    public NameSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    /**
     * Generates a random name for the peasant based on their gender
     */
    public void generateRandomName(RandomSource random) {
        String firstName;

        // Use gender-appropriate first names
        if (peasant.isFemale()) {
            firstName = FEMALE_FIRST_NAMES[random.nextInt(FEMALE_FIRST_NAMES.length)];
        } else {
            firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        }

        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String fullName = firstName + " " + lastName;

        peasant.setCustomName(Component.literal(fullName));
        peasant.setCustomNameVisible(false);
    }

    /**
     * Controls when the name tag is visible - only when a player is directly looking at the entity
     */
    public boolean isCustomNameVisible() {
        return peasant.hasCustomName() && shouldShowName();
    }

    /**
     * Checks if a player is looking directly at this entity
     */
    public boolean shouldShowName() {
        if (peasant.level().isClientSide) {
            return false;
        }
        return true;
    }

    public void saveData(CompoundTag compound, HolderLookup.Provider registryAccess) {
        if (peasant.hasCustomName()) {
            compound.putString("CustomName", Component.Serializer.toJson(peasant.getCustomName(), registryAccess));
        }
    }

    public void loadData(CompoundTag compound, HolderLookup.Provider registryAccess) {
        if (compound.contains("CustomName")) {
            try {
                Component customName = Component.Serializer.fromJson(compound.getString("CustomName"), registryAccess);
                if (customName != null) {
                    peasant.setCustomName(customName);
                    peasant.setCustomNameVisible(false);
                }
            } catch (Exception e) {
                if (!peasant.level().isClientSide) {
                    generateRandomName(peasant.level().getRandom());
                }
            }
        } else if (!peasant.hasCustomName() && !peasant.level().isClientSide) {
            generateRandomName(peasant.level().getRandom());
        }
    }
}