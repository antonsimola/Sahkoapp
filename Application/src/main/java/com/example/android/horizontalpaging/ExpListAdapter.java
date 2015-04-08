package com.example.android.horizontalpaging;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpListAdapter extends BaseExpandableListAdapter {

    private ArrayList<TyotehtavaOtsikko> data;
    private LayoutInflater minflater;
    private Fragment frag;

    public ExpListAdapter(ArrayList<TyotehtavaOtsikko> data) {
        this.data = data;
    }

    public void setInflater(LayoutInflater mInflater, Fragment frag) {
        this.minflater = mInflater;
        this.frag = frag;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TyotehtavaSisalto child = data.get(groupPosition).getSisalto();

        if (convertView == null) {
            convertView = minflater.inflate(R.layout.explist_sisalto, null);
        }
        TextView teht = (TextView) convertView.findViewById(R.id.tehtava);
        teht.setText(child.getTehtava());
        TextView aloitus = (TextView) convertView.findViewById(R.id.aloitus);
        aloitus.setText(child.getAloitus());
        TextView deadline = (TextView) convertView.findViewById(R.id.deadline);
        deadline.setText(child.getDeadline());
        TextView yhteyshlo = (TextView) convertView.findViewById(R.id.yhteyshenkilo);
        yhteyshlo.setText(child.getYhteyshenkilo());
        TextView muuta = (TextView) convertView.findViewById(R.id.muuta);
        muuta.setText(child.getMuuta());
        TextView osoite = (TextView) convertView.findViewById(R.id.osoite);
        osoite.setText(child.getOsoite());
        TextView linkki = (TextView) convertView.findViewById(R.id.ohj_piir_linkki);
        linkki.setText(child.getOhjPiirLinkki());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.explist_otsikko, null);
        }
        TextView pvm=(TextView) convertView.findViewById(R.id.pvm);
        pvm.setText(data.get(groupPosition).getPvm());
        TextView tyomaa=(TextView) convertView.findViewById(R.id.tyomaa);
        tyomaa.setText(data.get(groupPosition).getTyomaa());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}