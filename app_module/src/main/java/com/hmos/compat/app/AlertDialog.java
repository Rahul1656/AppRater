/*
 * Copyright (C) 2021 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hmos.compat.app;

import com.enrique.apprater.ResourceTable;
import ohos.agp.components.*;
import ohos.agp.utils.TextAlignment;
import ohos.agp.window.dialog.CommonDialog;
import ohos.agp.window.dialog.IDialog;
import ohos.app.Context;

public class AlertDialog extends CommonDialog implements IDialog {

    final Builder builder;

    private AlertDialog(Builder builder) {
        super(builder.getContext());
        this.builder = builder;
        ComponentContainer rootLayout = (ComponentContainer) LayoutScatter.getInstance(builder.getContext()).parse(getInflateLayout(), null, false);
        prepareDialogViewButton(rootLayout, builder);
        setContentCustomComponent(rootLayout);
    }

    /**
     * Builder Class.
     */
    public static class Builder {

        CharSequence title;

        CharSequence content;

        Button negativeButton;

        String negativeButtonText;

        Button neutralButton;

        String neutralButtonText;

        Button positiveButton;

        String positiveButtonText;

        private Context context;

        ClickedListener negativeClickedListener;

        ClickedListener neutralClickedListener;

        ClickedListener positiveClickedListener;

        AlertDialog alertDialog;

        public Builder(Context context) {
            this.context = context;
        }

        public final Context getContext() {
            return context;
        }

        public Builder setTitle(int titleRes) {
            this.title = context.getString(titleRes);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder content(int contentRes) {
            this.content = context.getString(contentRes);
            return this;
        }

        public Builder setMessage(String message) {
            this.content = message;
            return this;
        }

        public Builder setPositiveButton(Button button) {
            positiveButton = button;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, ClickedListener listener) {
            this.positiveButtonText = positiveButtonText;
            positiveClickedListener = listener;
            return this;
        }

        public Builder setNeutralButton(Button button) {
            neutralButton = button;
            return this;
        }

        public Builder setNeutralButton(String neutralButtonText, ClickedListener listener) {
            this.neutralButtonText = neutralButtonText;
            neutralClickedListener = listener;
            return this;
        }

        public Builder setNegativeButton(Button button) {
            negativeButton = button;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, ClickedListener listener) {
            this.negativeButtonText = negativeButtonText;
            negativeClickedListener = listener;
            return this;
        }

        public AlertDialog create() {
            alertDialog = new AlertDialog(this);
            return alertDialog;
        }

        public void show() {
            alertDialog.show();
        }
    }

    private int getInflateLayout() {
        return ResourceTable.Layout_alert_dialog_layout;
    }

    private void prepareDialogViewButton(ComponentContainer rootLayout, Builder builder) {
        DirectionalLayout titleFrame = AlertDialog.getComponent(rootLayout, ResourceTable.Id_titleFrame);
        Text tvTitle = AlertDialog.getComponent(rootLayout, ResourceTable.Id_title);
        Text tvContent = AlertDialog.getComponent(rootLayout, ResourceTable.Id_content);
        // Setup title and title frame
        if (tvTitle != null) {
            tvTitle.setTextAlignment(TextAlignment.START);
            if (builder.title == null) {
                if (titleFrame != null) {
                    titleFrame.setVisibility(Component.HIDE);
                }
            } else {
                tvTitle.setText(builder.title.toString());
                if (titleFrame != null) {
                    titleFrame.setVisibility(Component.VISIBLE);
                }
            }
        }
        if (tvContent != null) {
            if (builder.content != null) {
                tvContent.setText(builder.content.toString());
                tvContent.setVisibility(Component.VISIBLE);
            } else {
                tvContent.setVisibility(Component.HIDE);
            }
        }
        DirectionalLayout buttonLayout = AlertDialog.getComponent(rootLayout, ResourceTable.Id_btnLayout);
        if (builder.positiveButtonText != null) {
            this.setButton(2, builder.positiveButtonText, builder.positiveClickedListener);
        }
        if (builder.negativeButtonText != null) {
            this.setButton(1, builder.negativeButtonText, builder.negativeClickedListener);
        }
        if (builder.neutralButtonText != null) {
            this.setButton(0, builder.neutralButtonText, builder.neutralClickedListener);
        }
        if (builder.positiveButton != null) {
            buttonLayout.addComponent(builder.positiveButton);
        }
        if (builder.negativeButton != null) {
            buttonLayout.addComponent(builder.negativeButton);
        }
        if (builder.neutralButton != null) {
            buttonLayout.addComponent(builder.neutralButton);
        }
    }

    /**
     * getComponent
     *
     * @param root root component
     * @param id id of the component
     * @return component
     */
    public static <E extends Component> E getComponent(Component root, int id) {
        if (root == null) {
            return null;
        }
        try {
            return (E) root.findComponentById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }
}
