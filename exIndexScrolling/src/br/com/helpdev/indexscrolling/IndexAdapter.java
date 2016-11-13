/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpdev.indexscrolling;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class IndexAdapter extends ArrayAdapter<String> implements SectionIndexer {

    private HashMap<String, Integer> indexer;
    private String[] sections;

    public IndexAdapter(Context context, List<String> items) {
        super(context, android.R.layout.simple_list_item_1, items);
        indexer = new HashMap<String, Integer>();
        int size = items.size();
        for (int index = 0; index < size; index++) {
            String item = items.get(index);
            String chave = item.substring(0, 1);
            chave = chave.toUpperCase();
            if (!indexer.containsKey(chave)) {
                indexer.put(chave, index);
            }
        }

        ArrayList<String> sectionList = new ArrayList<String>(indexer.keySet());
        Collections.sort(sectionList);

        sections = new String[sectionList.size()];
        sections = sectionList.toArray(sections);
    }

    @Override
    public int getPositionForSection(int section) {
        return indexer.get(sections[section]);
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    @Override
    public Object[] getSections() {
        return sections;
    }
}
