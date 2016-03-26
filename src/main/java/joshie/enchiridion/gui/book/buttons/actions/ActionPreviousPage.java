package joshie.enchiridion.gui.book.buttons.actions;

import joshie.enchiridion.api.EnchiridionAPI;
import joshie.enchiridion.api.book.IButtonAction;
import joshie.enchiridion.api.book.IPage;

import java.util.List;

public class ActionPreviousPage extends AbstractAction {
    public ActionPreviousPage() {
        super("previous");
    }
    
    @Override
    public ActionPreviousPage copy() {
        ActionPreviousPage action = new ActionPreviousPage();
        action.tooltip = tooltip;
        action.hoverText = hoverText;
        action.unhoveredText = unhoveredText;
        return action;
    }

    @Override
    public IButtonAction create() {
        return new ActionPreviousPage();
    }

    @Override
    public void performAction() {
        try {
            List<IPage> pages = EnchiridionAPI.book.getBook().getPages();
            int number = EnchiridionAPI.book.getPage().getPageNumber() - 1;
            while (number >= 0) {
                for (IPage page : pages) {
                    if (page.getPageNumber() == number) {
                        EnchiridionAPI.book.setPage(page);
                        return; //Cancel further operations
                    }
                }

                number--;
            }

            //If we failed to find the next available page, reset the book to page 1

            IPage maxPage = null;
            for (IPage page : pages) {
                if (maxPage == null || page.getPageNumber() > maxPage.getPageNumber()) {
                    maxPage = page;
                }
            }

            EnchiridionAPI.book.setPage(maxPage);
        } catch (Exception e) {}
    }
}
