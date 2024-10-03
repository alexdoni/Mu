package com.facebook.share.internal;

import com.facebook.internal.Validate;
import com.facebook.share.model.GameRequestContent;

/* loaded from: classes.dex */
public class GameRequestValidation {
    public static void validate(GameRequestContent content) {
        Validate.notNull(content.getMessage(), ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        if ((content.getObjectId() != null) ^ (content.getActionType() == GameRequestContent.ActionType.ASKFOR || content.getActionType() == GameRequestContent.ActionType.SEND)) {
            throw new IllegalArgumentException("Object id should be provided if and only if action type is send or askfor");
        }
        int i = content.getRecipients() != null ? 1 : 0;
        if (content.getSuggestions() != null) {
            i++;
        }
        if (content.getFilters() != null) {
            i++;
        }
        if (i > 1) {
            throw new IllegalArgumentException("Parameters to, filters and suggestions are mutually exclusive");
        }
    }
}
