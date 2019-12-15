package com.gitlab.lae.intellij.actions.tree.json;

import com.gitlab.lae.intellij.actions.tree.ActionNode;
import com.gitlab.lae.intellij.actions.tree.When;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static javax.swing.KeyStroke.getKeyStroke;
import static org.junit.Assert.assertEquals;

public final class ActionNodeParserTest {

    @Test
    public void deserialization() throws Exception {
        var expected = asList(
                ActionNode.create(
                        "ActionsTree.Node.1",
                        "Unnamed",
                        null,
                        false,
                        When.toolWindowActive("Project"),
                        singletonList(getKeyStroke("ctrl C")),
                        singletonList(
                                ActionNode.create(
                                        "ActionsTree.Node.2",
                                        "Unnamed",
                                        null,
                                        false,
                                        When.ALWAYS,
                                        singletonList(getKeyStroke("P")),
                                        asList(
                                                ActionNode.create(
                                                        "CloseProject",
                                                        "Unnamed",
                                                        null,
                                                        false,
                                                        When.ALWAYS,
                                                        asList(
                                                                getKeyStroke(
                                                                        "K"),
                                                                getKeyStroke(
                                                                        "ctrl K")
                                                        ),
                                                        emptyList()
                                                ),
                                                ActionNode.create(
                                                        "OpenProjectGroup",
                                                        "Unnamed",
                                                        "SEP",
                                                        false,
                                                        When.ALWAYS,
                                                        singletonList(
                                                                getKeyStroke(
                                                                        "P")),
                                                        emptyList()
                                                )
                                        )
                                )
                        )
                ),
                ActionNode.create(
                        "ActionsTree.Node.3",
                        "b",
                        null,
                        false,
                        When.fileExtension("java"),
                        singletonList(getKeyStroke("ctrl X")),
                        singletonList(
                                ActionNode.create(
                                        "EditorSwapSelectionBoundaries",
                                        "Unnamed",
                                        null,
                                        true,
                                        When.ALWAYS,
                                        singletonList(getKeyStroke("ctrl X")),
                                        emptyList()
                                )
                        )
                ),
                ActionNode.create(
                        "ActionsTree.Node.4",
                        "c",
                        null,
                        false,
                        When.any(
                                When.fileExtension("txt"),
                                When.all(
                                        When.toolWindowActive("Run"),
                                        When.fileExtension("java")
                                )
                        ),
                        emptyList(),
                        emptyList()
                )
        );

        List<ActionNode> actual;
        try (var stream = ActionNodeParserTest.class
                .getResourceAsStream("test.json");
             Reader reader = new InputStreamReader(stream, UTF_8)) {
            actual = ActionNodeParser.parseJsonActions(reader);
        }

        assertEquals(expected, actual);
    }

}
