package org.hina.buoi5.Movies;

import org.hina.buoi5.RedBlackBST;

import java.util.Arrays;
import java.util.List;

public class MoviesST {
    private final RedBlackBST<String, List<String>> st;

    public MoviesST() {
        this.st = new RedBlackBST<>();
    }

    public void add(String[] strings) {
        st.put(strings[0],
                Arrays.stream(strings, 1, strings.length).toList());
    }

    public List<String> getActors(String movieName){
        return st.get(movieName);
    }
}
