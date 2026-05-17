package net.darkflameproduction.agotmod.data;

import java.util.Set;

public final class LoreHouses {

    private LoreHouses() {}

    public static final Set<String> NAMES = Set.of(
            // Great Houses
            "targaryen","stark","lannister","arryn","tully","greyjoy","baratheon","tyrell","martell",
            // Westeros
            "algood","allyrion","amber","ambrose","andrik","appleton","ashford","ashwood","baelish",
            "ball","banefort","baremmon","beesbury","belgrave","belmore","bettley","bigglestone",
            "blackbar","blackberry","blackfyre","blackmont","blackmyre","blacktyde","blackwood",
            "blanetree","blount","boggs","bole","bolling","bolton","borrell","bourney","botley",
            "bracken","branch","brax","breakstone","briar","bridges","brightstone","brook","broom",
            "broome","brownhill","brune","buckler","buckwell","bulwer","burley","bush","bushy",
            "butterwell","byrch","bywater","cafferen","cargyll","caron","cassel","casterly","caswell",
            "cave","celtigar","cerwyn","chambers","charlton","chelsted","chester","chyttering",
            "clegane","clifton","cobb","cockshaw","codd","coldwater","cole","condon","conklyn",
            "connington","corbray","cordwayner","costayne","cox","crabb","crakehall","crane","cray",
            "cressey","crowl","cupps","cuy","dalt","dargood","darke","darklyn","darkwood","darry",
            "dayne","deddings","doggett","dondarrion","donniger","drinkwater","drox","drumm",
            "dryland","dunn","durrandon","durwell","dustin","edgerton","egen","elesham","erenford",
            "errol","estermont","estren","falwell","farman","farring","farrow","farwynd","fell",
            "fenn","ferren","fisher","flint","florent","follard","foote","footly","forrester",
            "fossoway","fowler","foxglove","frey","frost","gardener","gargalen","garner","gaunt",
            "glover","goodbrook","goodbrother","goode","gower","graceford","grafton","grandison",
            "graves","greenfield","greengood","greenhill","greenwood","grell","grey","greyiron",
            "greystark","grimm","groves","haigh","hamell","harclay","hardy","hardyng","harlaw",
            "harroway","harte","hastwyck","hasty","hawick","hawthorne","hayford","herston","hersy",
            "hetherspoon","hewett","hightower","hoare","hogg","hollard","holt","hornwood","horpe",
            "hull","humble","hunt","hunter","hutcheson","inchfield","ironmaker","ironsmith","jast",
            "jordayne","justman","karstark","keath","kellington","kenning","kettleblack","kidwell",
            "knott","kyndall","ladybright","lake","langward","lannett","lanny","lansdale","lantell",
            "leek","lefford","leygood","liddle","lightfoot","lipps","locke","lolliston","long",
            "longthorpe","longwaters","lonmouth","lorch","lothston","lowther","lyberr","lychester",
            "lydden","lynderly","magnar","mallery","mallister","manderly","mandrake","manning",
            "manwoody","marbrand","marsh","massey","meadows","melcolm","merlyn","merryweather",
            "mertyns","middlebury","mollen","moore","mooton","moreland","mormont","morrigen","moss",
            "mudd","mullendore","musgood","myatt","myre","nayland","netley","norcross","norrey",
            "norridge","nute","nutt","oakheart","oldflowers","orkwood","orme","osgrey","overton",
            "paege","parren","payne","peake","peasebury","peat","peckledon","pemford","penny",
            "penrose","perryn","piper","plumm","polander","pommingham","poole","potter","prester",
            "pryor","pyle","pyne","qoherys","qorgyle","quagg","rambton","rankenfell","redbeard",
            "redding","redfort","redwyne","reed","reyne","rhysling","risley","rogers","rollingford",
            "roote","rosby","rowan","roxton","royce","ruskyn","ruthermont","ruttiger","ryder",
            "ryger","rykker","ryswell","saltcliffe","santagar","sarsfield","sawyer","seaworth",
            "selmy","serrett","serry","sharp","shawney","shell","shepherd","shermer","shett",
            "slate","sloane","slynt","smallwood","sparr","spicer","stackhouse","stackspear",
            "staedmon","stane","staunton","stokeworth","stonehouse","stonetree","stout","straw",
            "strickland","strong","suggs","sunderland","sunderly","sunglass","swann","sweet",
            "swyft","swygert","tallhart","tarbeck","tarly","tarth","tawney","teague","templeton",
            "terrick","thenn","thorne","toland","tollett","torrent","towers","toyne","trant",
            "tudbury","turnberry","uffering","uller","umber","upcliff","vaith","vance","varner",
            "velaryon","vikary","volmark","vypren","vyrwel","wade","wagstaff","warrick","waterman",
            "waxley","wayn","waynwood","weatherwax","weaver","webber","wells","wendwater",
            "wensington","westbrook","westerling","whent","whitehead","whitehill","whitfield",
            "willum","wode","woodfoot","woodhull","woods","woodwright","woolfield","wormwood",
            "wull","wydman","wyl","wylde","wynch","wythers","yarwyck","yelshire","yew","yronwood",
            // Essos
            "antaryon","fregar","haen","maegyr","otharys","otherys","paenymion","prestayn",
            "qhaedar","reyaan","rogare","staegone","tagaros","vaelaros","vhassar",
            // Ghiscar
            "ahlaq","dhazak","eraz","faez","galare","ghazeen","hazkar","kandaq","loraq","merreq",
            "myraq","nakloz","naqqan","pahl","qaggaz","quazzar","reznak","rhaezn","rhazdar",
            "uhlez","ullhor","yherizan","yunzak","zhak","zherzyn",
            // Summer Isles
            "qhoqua","qo","xaq","xho",
            // Adaptations
            "blackbrow","branfield","brownbarrow","caulfield","dormand","elliver","glenmore",
            "grayson","greenleaf","harlton","mazin","morgryn","sarwyck","tarwick","westford",
            "wibberley"
    );

    public static boolean isLoreHouse(String name) {
        if (name == null || name.isBlank()) return false;
        // Strip "House " prefix and "of X" suffix before checking
        String normalized = name.trim().toLowerCase();
        if (normalized.startsWith("house ")) normalized = normalized.substring(6).trim();
        int ofIdx = normalized.indexOf(" of ");
        if (ofIdx != -1) normalized = normalized.substring(0, ofIdx).trim();
        return NAMES.contains(normalized);
    }
}