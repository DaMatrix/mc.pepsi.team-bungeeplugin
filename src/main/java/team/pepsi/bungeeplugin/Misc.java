package team.pepsi.bungeeplugin;

import com.google.common.collect.Lists;

import java.util.List;

public class Misc {
    public static final String[] PEPSI_MEMBERS = new String[]{
            "8f8cef60-1f3a-4778-849d-5dab58c46639",
            "a4f77739-d15e-4dc2-b957-219a2f6f9244",
            "2f8731ca-c2a7-454e-85b6-6d072ed199c1",
            "4c8e844e-43ab-4d39-a62c-56fc02dda031",
            "4f27dd06-b5e1-44ff-8edf-2c5135b74489",
            "65f815b5-17b0-4c68-9aa3-91b68379fc6d",
            "266cae9f-b230-4fa6-b4d3-66c17755e3e5",
            "2d9d43d8-cb19-48a1-85c1-c7ccb2676d85",
            "02a8e07d-ce7f-46e2-a7ab-e994a940ae73",
            "8c7f2df9-48df-460c-853f-26b98bfd160f",
            "d16acb1b-8fb2-46dd-a561-4e9b9b557523",
            "95ddfd3a-d40e-4fc8-a408-d3ee6995706d",
            "a3166d0a-bcfd-406c-9ca8-4693e771d7e2",
            "26d7502f-fa48-4e0c-a3fd-637433f42f80",
            "475b83d1-5189-4bb8-88d4-f2922c0c8d58",
            "71832324-a62f-4ed4-a86e-4c4b8a3226dd",
            "90c66ec2-d931-4f1f-b2da-328afc9fe854",
            "69427358-99e6-4e4e-94ed-4a669ba6a8da",
            "0c3959df-4667-4bc5-b47c-a756689764f4",
            "a3b69979-9248-4a2e-979f-bc992b29a9f6",
            "773e431a-cc3a-41d4-90db-e749f579fff8",
            "b372c514-1d6a-4f86-b1c9-f06bac38690a",
            "c88f6974-a324-4e85-bd54-975a6aa03e75",
            "4b052543-9f20-4636-939e-d8dc05a53b3f",
            "4fd69819-b260-4ff4-af8a-172073fa7d5f",
            "fdee323e-7f0c-4c15-8d1c-0f277442342a",
            "8c7f2df9-48df-460c-853f-26b98bfd160f",
            "49f99d0a-cd48-4faa-a72e-d3b1552e95f1",
            "8034d01d-bc3b-49e2-a6f6-29455d0a5f24",
            "104f192a-0f3e-41e3-b574-919cc931559a",
            "6a711553-4287-478b-9b84-9ec1e01715a2",
            "4495eebb-7a4e-43aa-9784-02ea86f705ed",
            "1e8c7d13-9118-41e2-b334-fdb213970135",
            "12736215-9375-4db9-9b32-2db5107507e9",
            "c64bbc02-db10-418e-ac9e-4911922520db"
    };
    public static final String[] YOUTUBE = new String[] {
            "95ddfd3a-d40e-4fc8-a408-d3ee6995706d",
            "855f0807-94a6-407d-90c2-0c3de4447f52",
            "12736215-9375-4db9-9b32-2db5107507e9"
    };
    public static final String[] OWNER = new String[]   {
            "4fd69819-b260-4ff4-af8a-172073fa7d5f",
            "c88f6974-a324-4e85-bd54-975a6aa03e75"
    };
    public static final String PEPSI_BOY = "8f8cef60-1f3a-4778-849d-5dab58c46639";
    public static final List<String> BEPIS_WHITELIST;

    static {
        List<String> list = Lists.newArrayList(PEPSI_MEMBERS);
        list.add("60381045-1164-490f-819e-db8e22a3ded7");
        list.add("855f0807-94a6-407d-90c2-0c3de4447f52");
        list.add("361d4917-f038-4abd-93d3-59d869fca655");
        list.add("d533e1fe-eeec-4c72-909b-c35e8102c9d4");
        list.add("ae0882be-0ace-4193-b342-d8243765a668");
        BEPIS_WHITELIST = list;
    }
    public static int maxCountHallway = 25;
}
