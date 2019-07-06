package de.ehmkah.projects.diffdeluxe;

import com.intellij.diff.actions.CompareFilesAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * @author Michael Krausse (ehmkah)
 */
public class CallDiffDeluxe extends CompareFilesAction {
  // If you register the action from Java code, this constructor is used to set the menu item name
  // (optionally, you can specify the menu description and an icon to display next to the menu item).
  // You can omit this constructor when registering the action in the plugin.xml file.
  public CallDiffDeluxe() {
    // Set the menu item name.
    super();
    // Set the menu item name, description and icon.
    // super("Text _Boxes","Item description",IconLoader.getIcon("/Mypackage/icon.png"));
  }

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    super.actionPerformed(e);
  }


  @Override
  public void update(@NotNull AnActionEvent e) {
    super.update(e);
    e.getPresentation().setText("Diff Deluxe");
  }

  /**public void actionPerformed( AnActionEvent   event) {
    Project project = event.getData(PlatformDataKeys.PROJECT);
    String txt= Messages.showInputDialog(project, "What is your name?", "Input your name", Messages.getQuestionIcon());
    Messages.showMessageDialog(project, "Hello, " + txt + "!\n I am glad to see you.", "Information", Messages.getInformationIcon());
  }  **/
}
