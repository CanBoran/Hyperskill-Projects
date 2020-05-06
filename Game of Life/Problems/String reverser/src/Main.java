StringReverser reverser = new StringReverser() {
    public String reverse(String str){
        StringBuilder input1 = new StringBuilder();
        input1.append(str);

        return input1.reverse().toString();
        }
        };/* create an instance of an anonymous class here,
                             do not forget ; on the end */