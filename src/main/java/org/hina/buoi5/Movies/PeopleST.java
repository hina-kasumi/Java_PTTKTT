package org.hina.buoi5.Movies;

import org.hina.buoi5.RedBlackBST;

import java.util.ArrayList;
import java.util.List;

public class PeopleST {
    private final RedBlackBST<String, List<String>> st;

    public PeopleST() {
        this.st = new RedBlackBST<>();
    }

    public void add(String[] strings) {
        for (int i = 1; i < strings.length; i++) {
            List<String> list = st.get(strings[i]);
            if (list == null) {
                list = new ArrayList<>();
                st.put(strings[i], list);
            } else {
                st.get(strings[i]);
            }
            list.add(strings[0]);
        }
    }

    public List<String> getMovies(String actorName) {
        return st.get(actorName);
    }
}
