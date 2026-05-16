package net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;

public class NameSystem {
    private final Peasant_Entity peasant;

    // ── First names ───────────────────────────────────────────────────────────

    private static final String[] FIRST_NAMES = {
            "Alastair", "Angus", "Arran", "Blair", "Brody", "Callum", "Campbell", "Cameron", "Cormag", "Craig",
            "Dougal", "Duncan", "Euan", "Ewan", "Fergus", "Finlay", "Fraser", "Gavin", "Gordon", "Graeme",
            "Gregor", "Hamish", "Harris", "Hugh", "Iain", "Innes", "Jamie", "Keith", "Kenneth", "Kerr",
            "Knox", "Lachlan", "Lennox", "Leslie", "Logan", "Malcolm", "Murray", "Neil", "Niall", "Ramsay",
            "Robbie", "Ross", "Ruaridh", "Seamus", "Sholto", "Struan", "Tavish", "Torin", "Wallace", "Watt",
            "Alec", "Archibald", "Baird", "Brodie", "Bruce", "Calum", "Conall", "Dalziel", "Donald", "Donnie",
            "Drummond", "Duff", "Eachann", "Erskine", "Farquhar", "Findlay", "Gilchrist", "Gillespie", "Grant",
            "Hector", "Ivor", "Jock", "John", "Kennan", "Kyle", "Leith", "Lorne", "Magnus", "Mathew",
            "Maxwell", "Menzies", "Munro", "Murdo", "Nairn", "Paton", "Quinn", "Ranald", "Roderick", "Ronald",
            "Roy", "Shaw", "Sloan", "Stewart", "Tam", "Taran", "Torquil", "Uisdean", "Watson", "Wilson",
            "Eddard", "Robb", "Brandon", "Rickon", "Benjen", "Torrhen", "Cregan", "Rickard", "Harrion", "Arnolf",
            "Karlon", "Roose", "Domeric", "Galbart", "Robett", "Mors", "Hother", "Jon", "Jeor", "Jorah",
            "Rodrik", "Osric", "Dorren", "Theon", "Wyman", "Cley", "Howland", "Halys", "Leobald", "Donnel",
            "Robin", "Ludd", "Gryff", "Gregor", "Asher", "Ethan", "Arthur", "Eddison", "Vayon", "Wendel",
            "Jonos", "Jory", "Bowen", "Richard", "Dickon", "Roger",
            "Alebelly", "Alyn", "Bandy", "Bannen", "Barth", "Bartimus", "Bartogon", "Belthasar", "Ben", "Benfred",
            "Benjicot", "Bennard", "Benton", "Beron", "Beren", "Bran", "Cayn", "Calon", "Chayle", "Crowl",
            "Damon", "Desmond", "Donnis", "Daryn", "Edderion", "Edric", "Edrick", "Edwyle", "Edwyn", "Ellard",
            "Elric", "Errold", "Eyron", "Farlen", "Gage", "Gariss", "Gareth", "Garth", "Gawen", "Gaven",
            "Grunt", "Hallis", "Harwin", "Hayhead", "Heward", "Hodor", "Hoarfrost", "Hullen", "Jack",
            "Jocelyn", "Jonnel", "Jojen", "Joseth", "Larence", "Lonnel", "Lew", "Mark", "Marlon", "Maynard",
            "Medger", "Medrick", "Mikken", "Morgan", "Murch", "Nage", "Ned", "Ondrew", "Owen", "Porther",
            "Quent", "Rat", "Robard", "Robyn", "Rodwell", "Rogar", "Ronnel", "Royce",
            "Shadd", "Sherrit", "Slate", "Steelshanks", "Theodan", "Theo", "Therry", "Timotty",
            "Tomard", "Torghen", "Torren", "Varly", "Warrick", "Wayn", "Will", "Willam", "Wyl", "Wylis", "Wynton"
    };

    private static final String[] CHILD_MALE_FIRST_NAMES = {
            "Alyn", "Artos", "Bran", "Ben", "Benjen", "Brandon", "Collin", "Dickon", "Duncan", "Edric",
            "Eddard", "Elric", "Gareth", "Gendry", "Harwin", "Hodor", "Jon", "Jojen", "Jory",
            "Lommy", "Ned", "Owen", "Rickon", "Robb", "Robin", "Rodrik", "Tommen", "Waymar", "Will", "Wyl",
            "Aedan", "Aldric", "Ansel", "Aron", "Baelor", "Bennet", "Beric", "Brynden", "Calon", "Cley",
            "Daemon", "Dareon", "Desmond", "Donal", "Donnel", "Edwyn", "Eldon", "Ellard", "Elyas",
            "Emmon", "Ethan", "Franklyn", "Garrett", "Harys", "Helman", "Humfrey", "Jasper", "Jeffory",
            "Kevan", "Lucas", "Lyonel", "Martyn", "Myles", "Norbert", "Osmund", "Quentyn",
            "Renly", "Rickard", "Ronnel", "Steffon", "Terrance", "Theon", "Theo", "Tristan", "Ulmer", "Walton"
    };

    private static final String[] CHILD_FEMALE_FIRST_NAMES = {
            "Arya", "Bessa", "Beth", "Cat", "Cersei", "Daisy", "Elia", "Gilly",
            "Joy", "Lyanna", "Mya", "Myrcella", "Nan", "Nymeria", "Penny", "Roslin", "Sansa", "Shireen",
            "Talla", "Ygritte", "Ysilla",
            "Alla", "Alarra", "Alerie", "Alys", "Amerei", "Arwyn", "Ashara", "Betha", "Brienne", "Catelyn",
            "Delena", "Denyse", "Dyanna", "Elinor", "Ellyn", "Falena", "Genna", "Gwyneth", "Hanna",
            "Helena", "Janei", "Jayne", "Jocelyn", "Karla", "Lanna", "Leyla", "Lynesse", "Malora",
            "Mariah", "Marissa", "Meredyth", "Minisa", "Morya", "Naerys", "Pia", "Rhaella", "Rhonda",
            "Roslyn", "Selyse", "Sybelle", "Taena", "Tanda", "Tya", "Tyanna", "Walda",
            "Ally", "Annie", "Betsy", "Bonnie", "Cate", "Dolly", "Effie", "Ellie", "Emmy",
            "Faye", "Georgie", "Gracie", "Hattie", "Izzy", "Josie", "Katie", "Lettie", "Maggie", "Millie",
            "Molly", "Nettie", "Ollie", "Polly", "Rosie", "Sally", "Susie", "Tilly", "Winnie", "Zoe"
    };

    private static final String[] FEMALE_FIRST_NAMES = {
            "Alys", "Alarra", "Alysane", "Alysanne", "Arra", "Aregelle", "Arrana", "Arsa", "Arya",
            "Barba", "Barbrey", "Bessa", "Beth", "Bethany", "Dacey", "Danny", "Donella", "Erena",
            "Gilliane", "Jeyne", "Jessamyn", "Jez", "Jonelle", "Jorelle", "Jyana", "Kyra", "Lyanne",
            "Lysara", "Lysa", "Lyanna", "Lyessa", "Lyra", "Maege", "Maisie", "Margaret", "Mara",
            "Marna", "Meera", "Meliana", "Myriame", "Palla", "Sara", "Sybelle", "Wylla", "Wynafryd",
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
            "Aileas", "Aileen", "Aine", "Bronwen", "Caoimhe", "Catelyn", "Ciara", "Davina", "Delyth",
            "Effie", "Eilidh", "Eirlys", "Eleri", "Elen", "Ffion", "Gladys", "Gwen", "Gwenllian",
            "Hafwen", "Heledd", "Hestia", "Ishbel", "Kenna", "Kirsty",
            "Lorna", "Mairi", "Megan", "Morag", "Morven", "Moyra", "Muirenn", "Nessa", "Nesta",
            "Olwen", "Paisley", "Peigi", "Rhona", "Shona", "Sine", "Skye", "Sorcha", "Tegan",
            "Thea", "Ulrika", "Vaila", "Vivian", "Wenna", "Ysabel", "Zara"
    };

    // ── Nicknames ─────────────────────────────────────────────────────────────

    private static final String[] NICKNAMES = {
            // Physical appearance
            "Dirty", "Fat", "Old", "Young", "Big", "Little", "Long", "Short", "Red", "Black",
            "Grey", "Blind", "Lame", "Thin", "Stout", "Bald", "Hairy", "Pale", "Dark", "Tall",
            "Small", "Crooked", "Twisted", "Scarred", "Broken", "One-Eye", "Limping", "Hunched",
            "Pox-Face", "Pudgy", "Gaunt", "Hollow", "Stout", "Wiry", "Stumpy", "Lanky", "Ruddy",
            "Sallow", "Spotty", "Shaggy", "Bristly", "Grizzled", "Wrinkled", "Freckled", "Bearded",
            // Personality
            "Proud", "Bold", "Brave", "Slow", "Swift", "Strong", "Weak", "Loud", "Quiet",
            "Gentle", "Rough", "Drunk", "Sober", "Mad", "Wise", "Merry", "Grim", "Honest",
            "False", "Sour", "Dull", "Sharp", "Crafty", "Simple", "Clever", "Foolish", "Sly",
            "Coward", "Humble", "Pious", "Greedy", "Generous", "Miserly", "Reckless", "Patient",
            "Sullen", "Cheerful", "Bitter", "Proud", "Lazy", "Eager", "Wary", "Fearless",
            "Cruel", "Kind", "Cunning", "Stubborn", "Fickle", "Loyal", "Faithless", "Jealous",
            // Miscellaneous
            "Lucky", "Unlucky", "Cursed", "Blessed", "Muddy", "Dusty", "Smoky", "Bloody",
            "Ragged", "Tattered", "Hollow", "Empty", "Wooden", "Stony", "Iron", "Silver",
            "Golden", "Copper", "Rusty", "Mossy", "Thorny", "Frozen", "Burnt", "Ashen",
            "Damp", "Salty", "Sweet", "Sour", "Bitter", "Spicy", "Slimy", "Knotty"
    };

    // ── Job last names ────────────────────────────────────────────────────────

    private static final String[] FARMER_NAMES     = { "Farmer", "Ackerman", "Bauer", "Bond", "Cottar", "Plowman", "Tiller", "Granger", "Hayward", "Cropper" };
    private static final String[] GROCER_NAMES     = { "Chapman", "Mercer", "Merchant", "Spencer", "Spicer", "Draper", "Cheesman", "Chandler" };
    private static final String[] BUTCHER_NAMES    = { "Butcher", "Kellogg", "Fleischer", "Metcalf", "Slaughter", "Skinner" };
    private static final String[] TANNER_NAMES     = { "Tanner", "Barker", "Currier", "Skinner", "Leathersmith", "Peltier", "Garber" };
    private static final String[] TAILOR_NAMES     = { "Taylor", "Schneider", "Clothier", "Draper", "Weaver", "Fuller", "Tucker" };
    private static final String[] BLACKSMITH_NAMES = { "Smith", "Blacksmith", "Smyth", "Faber", "Schmidt", "Kovacs", "Kowalski", "Ironmonger", "Steele" };
    private static final String[] GUARD_NAMES      = { "Ward", "Warden", "Constable", "Bailey", "Waite", "Watchman", "Keepe" };
    private static final String[] MINER_NAMES      = { "Miner", "Collier", "Pitman", "Hodder", "Eisenhauer", "Digger", "Delver" };
    private static final String[] SMELTER_NAMES    = { "Bloomer", "Steele", "Brenner", "Stoker", "Smelter", "Forger", "Ironsmith" };
    private static final String[] LUMBERJACK_NAMES = { "Woodward", "Forester", "Grover", "Waldman", "Holtzman", "Timber", "Forster", "Woodman" };
    private static final String[] CHARCOAL_NAMES   = { "Coleman", "Collier", "Kohler", "Brenner", "Ashman", "Coalman" };
    private static final String[] CATTLE_NAMES     = { "Howard", "Coward", "Herdman", "Drover", "Bullock", "Oxman" };
    private static final String[] CHICKEN_NAMES    = { "Fowler", "Hatcher", "Poulter", "Eggler", "Chicker", "Feather" };
    private static final String[] PIG_NAMES        = { "Hogg", "Swynford", "Porker", "Pigg", "Swineherd", "Bacon" };
    private static final String[] SHEEP_NAMES      = { "Shepherd", "Shepard", "Woolman", "Shearer", "Fleece", "Lambe", "Sherman" };

    // ── Instance state ────────────────────────────────────────────────────────

    private String firstName  = "";
    private String nickname   = "";   // empty = no nickname
    private String lastName   = "";   // empty = no job yet

    public NameSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    // ── Public API ────────────────────────────────────────────────────────────

    /**
     * Generates a first name (and optional nickname) on spawn. No last name yet.
     */
    public void generateRandomName(RandomSource random) {
        if (peasant.isChild()) {
            if (peasant.isFemale()) {
                firstName = CHILD_FEMALE_FIRST_NAMES[random.nextInt(CHILD_FEMALE_FIRST_NAMES.length)];
            } else {
                firstName = CHILD_MALE_FIRST_NAMES[random.nextInt(CHILD_MALE_FIRST_NAMES.length)];
            }
        } else {
            if (peasant.isFemale()) {
                firstName = FEMALE_FIRST_NAMES[random.nextInt(FEMALE_FIRST_NAMES.length)];
            } else {
                firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            }
        }

        // 50% chance of a nickname
        nickname = random.nextBoolean() ? NICKNAMES[random.nextInt(NICKNAMES.length)] : "";
        lastName = ""; // no job yet

        applyName();
    }

    /**
     * Called when the NPC gets a job. Assigns a job-appropriate last name.
     */
    public void onJobAssigned(String jobType, RandomSource random) {
        lastName = pickLastName(jobType, random);
        applyName();
    }

    /**
     * Called when the NPC loses their job. Clears the last name.
     */
    public void onJobLost() {
        lastName = "";
        applyName();
    }

    // ── Name assembly ─────────────────────────────────────────────────────────

    private void applyName() {
        String full;
        if (!lastName.isEmpty() && !nickname.isEmpty()) {
            // "Carl Draper the Dirty"
            full = firstName + " " + lastName + " the " + nickname;
        } else if (!lastName.isEmpty()) {
            // "Carl Draper"
            full = firstName + " " + lastName;
        } else if (!nickname.isEmpty()) {
            // "Dirty Carl" — no job yet, nickname goes before first name
            full = nickname + " " + firstName;
        } else {
            // "Carl"
            full = firstName;
        }
        peasant.setCustomName(net.minecraft.network.chat.Component.literal(full));
        peasant.setCustomNameVisible(false);
    }

    private String pickLastName(String jobType, RandomSource random) {
        String[] pool = switch (jobType) {
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_FARMER          -> FARMER_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_GROCER          -> GROCER_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_BUTCHER         -> BUTCHER_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_TANNER          -> TANNER_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_TAILOR          -> TAILOR_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_BLACKSMITH      -> BLACKSMITH_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_GUARD           -> GUARD_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_MINER           -> MINER_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_SMELTER         -> SMELTER_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_LUMBERJACK      -> LUMBERJACK_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_CHARCOAL_BURNER -> CHARCOAL_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_CATTLE_HERDER   -> CATTLE_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_CHICKEN_BREEDER -> CHICKEN_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_PIG_BREEDER     -> PIG_NAMES;
            case net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_SHEEP_HERDER    -> SHEEP_NAMES;
            default -> new String[]{};
        };
        if (pool.length == 0) return "";
        return pool[random.nextInt(pool.length)];
    }

    // ── Visibility ────────────────────────────────────────────────────────────

    public boolean isCustomNameVisible() {
        return peasant.hasCustomName() && shouldShowName();
    }

    public boolean shouldShowName() {
        return !peasant.level().isClientSide;
    }

    // ── Save / Load ───────────────────────────────────────────────────────────

    public void saveData(CompoundTag compound, HolderLookup.Provider registryAccess) {
        compound.putString("NpcFirstName", firstName);
        compound.putString("NpcNickname",  nickname);
        compound.putString("NpcLastName",  lastName);
    }

    public void loadData(CompoundTag compound, HolderLookup.Provider registryAccess) {
        if (compound.contains("NpcFirstName")) {
            firstName = compound.getString("NpcFirstName");
            nickname  = compound.contains("NpcNickname") ? compound.getString("NpcNickname") : "";
            lastName  = compound.contains("NpcLastName")  ? compound.getString("NpcLastName")  : "";
            applyName();
        } else if (compound.contains("CustomName")) {
            // Legacy fallback — old saves had the full name stored directly
            try {
                net.minecraft.network.chat.Component customName =
                        net.minecraft.network.chat.Component.Serializer.fromJson(
                                compound.getString("CustomName"), registryAccess);
                if (customName != null) {
                    String full = customName.getString();
                    // Best-effort parse: treat last word as last name, rest as first name
                    String[] parts = full.split(" ");
                    if (parts.length >= 2) {
                        firstName = parts[0];
                        lastName  = parts[parts.length - 1];
                        nickname  = "";
                    } else {
                        firstName = full;
                        lastName  = "";
                        nickname  = "";
                    }
                    applyName();
                }
            } catch (Exception e) {
                if (!peasant.level().isClientSide) generateRandomName(peasant.level().getRandom());
            }
        } else if (!peasant.hasCustomName() && !peasant.level().isClientSide) {
            generateRandomName(peasant.level().getRandom());
        }
    }

}