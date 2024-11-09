package store.controller;

import store.service.MembershipService;
import store.util.InputUtil;
import store.validator.InputValidator;
import store.view.InputView;

public class MembershipController {
    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    public void processMembership() {
        InputUtil.doLoop(() -> {
            String answer = InputView.readMembershipUsed();
            InputValidator.OnlyYesOrNo(answer);

            if (answer.equals("Y")) {
                membershipService.useMembership();
            }

            return null;
        });
    }
}
