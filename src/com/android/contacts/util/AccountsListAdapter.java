/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.contacts.util;

import com.android.contacts.R;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.AccountType;

import android.accounts.Account;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * List-Adapter for Account selection
 */
public final class AccountsListAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private final List<Account> mAccounts;
    private final AccountTypeManager mAccountTypes;
    private final Context mContext;

    public AccountsListAdapter(Context context, boolean writableOnly) {
        mContext = context;
        mAccountTypes = AccountTypeManager.getInstance(context);
        mAccounts = mAccountTypes.getAccounts(writableOnly);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View resultView = convertView != null ? convertView
                : mInflater.inflate(R.layout.account_selector_list_item, parent, false);

        final TextView text1 = (TextView)resultView.findViewById(android.R.id.text1);
        final TextView text2 = (TextView)resultView.findViewById(android.R.id.text2);
        final ImageView icon = (ImageView)resultView.findViewById(android.R.id.icon);

        final Account account = mAccounts.get(position);
        final AccountType accountType = mAccountTypes.getAccountType(account.type);

        text1.setText(account.name);
        text2.setText(accountType.getDisplayLabel(mContext));
        icon.setImageDrawable(accountType.getDisplayIcon(mContext));

        return resultView;
    }

    @Override
    public int getCount() {
        return mAccounts.size();
    }

    @Override
    public Account getItem(int position) {
        return mAccounts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

