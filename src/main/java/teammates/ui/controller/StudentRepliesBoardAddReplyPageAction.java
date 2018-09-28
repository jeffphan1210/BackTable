
package teammates.ui.controller;

import teammates.common.datatransfer.attributes.RepliesAttributes;
import teammates.common.datatransfer.attributes.TopicAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.exception.InvalidParametersException;
import teammates.common.util.Assumption;
import teammates.common.util.Const;
import teammates.common.util.Logger;
import teammates.ui.pagedata.StudentRepliesBoardAddReplyPage;
import teammates.ui.pagedata.StudentRepliesBoardPageData;

import java.util.UUID;

/**
 * Action: showing the profile page for a student in a course.
 */
public class StudentRepliesBoardAddReplyPageAction extends Action {

    StudentRepliesBoardPageData data;
    TopicAttributes topic;
    private static final Logger log = Logger.getLogger();

    @Override
    protected ActionResult execute() {
        //getting topic name at the moment, whereas would be better to use topic id
        String topicId = getRequestParamValue(Const.ParamsNames.TOPIC_ID);
        Assumption.assertPostParamNotNull(Const.ParamsNames.TOPIC_ID, topicId);
        String replyDesc = getRequestParamValue(Const.ParamsNames.REPLY_DESC);
        Assumption.assertPostParamNotNull(Const.ParamsNames.REPLY_DESC, replyDesc);
        topic = logic.getTopic(topicId);
        
        RepliesAttributes newReply = new RepliesAttributes(replyDesc, account.getName(), topic.getCount());
        topic.addReply(newReply);
        try {
            logic.updateTopic(topic);
        } catch (InvalidParametersException e) {
            e.printStackTrace();
        } catch (EntityDoesNotExistException e) {
            e.printStackTrace();
        }
        
        data = new StudentRepliesBoardPageData(account, sessionToken);
        System.out.println(account.getName());
        data.init(topic);
        return createShowPageResult(Const.ViewURIs.STUDENT_REPLIES_BOARD_PAGE, data);
    }

}

