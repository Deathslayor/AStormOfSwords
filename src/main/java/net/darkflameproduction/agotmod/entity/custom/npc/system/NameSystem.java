package net.darkflameproduction.agotmod.entity.custom.npc.system;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;

public class NameSystem {
    private final Northern_Peasant_Entity peasant;

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

    public NameSystem(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    /**
     * Generates a random name for the peasant
     */
    public void generateRandomName(RandomSource random) {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
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