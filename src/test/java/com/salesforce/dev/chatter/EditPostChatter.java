package com.salesforce.dev.chatter;

import com.salesforce.dev.framework.dto.Chatter;
import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.framework.utils.JSONMapper;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.chatter.ChatterHome;
import com.salesforce.dev.pages.chatter.EditPost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * This class will be used to test the edition of a post chatter.
 *
 * @author Veronica Prado.
 * @author Bruno Barrios.
 * @since 9/3/2015
 */
public class EditPostChatter {

    private static final JSONMapper JSON_MAPPER_INSTANCE = JSONMapper.getInstance();

    private ChatterHome chatterHome;
    private Chatter createChatter;

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        createChatter = getChatter("Chatter.json");
        MainPage mainPage = LoginPage.loginAsPrimaryUser();
        NavigationBar navigationBar = mainPage.gotoNavBar();
        chatterHome = navigationBar.goToChatterHome();
        chatterHome.clickPost()
                .setPost(createChatter.getPost())
                .clickShareBtn();
    }

    @Test(groups = {"Acceptance"})
    public void CreatePostAndComment() {
        Chatter chatter = getChatter("EditChatter.json");
        EditPost editPost = chatterHome.editPost(createChatter.getPost());
        editPost.setEditTextBox(chatter.getPost());
        chatterHome = editPost.clickSaveEditBtn();
        Assert.assertTrue(chatterHome.VerifyPostCreated(chatter.getPost()), "Post has not been Updated");
    }

    private Chatter getChatter(String fileJson) {
        Chatter chatter = (Chatter) JSON_MAPPER_INSTANCE.getGeneric(new Chatter(),fileJson);
        return  chatter;
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        chatterHome.DeletePost();
    }
}
