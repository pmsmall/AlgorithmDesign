package com.common.UI.listener;

import com.common.UI.theme.ButtonTheme.buttonActiveStatus;
import com.common.UI.theme.ButtonTheme.buttonStatus;

public class ButtonStatusChangeEvent {
	buttonActiveStatus activeStatus;
	buttonStatus status;
	int change;
	public final static int ACTIVESTATUS_CHANGE = 1;
	public final static int STATUS_CHANGE = 2;
	public final static int BOTH_CHANGE = 3;

	public ButtonStatusChangeEvent(buttonActiveStatus activeStatus, buttonStatus status, int change) {
		this.activeStatus = activeStatus;
		this.status = status;
		this.change = change;
	}

	public boolean isStatusChange() {
		return STATUS_CHANGE == change || BOTH_CHANGE == change;
	}

	public boolean isActiveStatusChange() {
		return ACTIVESTATUS_CHANGE == change || BOTH_CHANGE == change;
	}

	public buttonStatus getStatus() {
		return status;
	}

	public buttonActiveStatus getActiveStatus() {
		return activeStatus;
	}

	public boolean isActive() {
		return activeStatus == buttonActiveStatus.active;
	}
}
