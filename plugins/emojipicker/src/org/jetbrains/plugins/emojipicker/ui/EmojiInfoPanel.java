// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.plugins.emojipicker.ui;

import com.intellij.ide.ui.AntialiasingType;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.ui.scale.JBUIScale;
import org.jetbrains.annotations.Nls;
import org.jetbrains.plugins.emojipicker.Emoji;
import org.jetbrains.plugins.emojipicker.EmojiSkinTone;
import org.jetbrains.plugins.emojipicker.service.EmojiService;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;

public class EmojiInfoPanel extends JPanel {
  private final EmojiPickerStyle myStyle;
  @NlsSafe private String myCurrentEmoji;
  @Nls private String myCurrentEmojiName;

  EmojiInfoPanel(EmojiPickerStyle style) {
    myStyle = style;
    Dimension size = new Dimension(0, JBUIScale.scale(51));
    setPreferredSize(size);
    setMinimumSize(size);
  }

  @Override
  public void paintComponent(Graphics g) {
    ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, AntialiasingType.getKeyForCurrentScope(false));
    g.setColor(myCurrentEmoji != null ? myStyle.myToolbarColor : myStyle.myBackgroundColor);
    g.fillRoundRect(0, 0, getWidth(), getHeight(), 6, 6);
    g.fillRect(0, 0, getWidth(), getHeight() - 6);
    if (myCurrentEmoji != null) {
      g.setColor(myStyle.myBorderColor);
      g.drawLine(0, 0, getWidth(), 0);
      new TextLayout(myCurrentEmoji, myStyle.myEmojiFont, ((Graphics2D)g).getFontRenderContext())
        .draw((Graphics2D)g, 16, getHeight() - 17);
      if (myCurrentEmojiName != null) {
        g.setFont(myStyle.myFont);
        g.setColor(myStyle.myTextColor);
        g.drawString(myCurrentEmojiName, 49, getHeight() - 20);
      }
    }
  }

  void showEmojiInfo(Emoji emoji, EmojiSkinTone skinTone) {
    if (emoji == null) {
      myCurrentEmoji = myCurrentEmojiName = null;
    }
    else {
      myCurrentEmoji = emoji.getTonedValue(skinTone);
      myCurrentEmojiName = EmojiService.getInstance().findNameForEmoji(emoji);
    }
    repaint();
  }
}
