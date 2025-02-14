/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.modules.gradle.javaee.web.newproject;

import java.util.LinkedList;
import java.util.List;
import org.netbeans.modules.gradle.javaee.api.ui.support.DisplayNameListCellRenderer;
import org.netbeans.modules.gradle.javaee.api.ui.support.JavaEEServerComboBoxModel;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.j2ee.core.Profile;
import org.netbeans.modules.j2ee.deployment.devmodules.api.J2eeModule;
import org.netbeans.modules.j2ee.deployment.devmodules.api.J2eePlatform;
import org.netbeans.modules.j2ee.deployment.devmodules.api.ServerManager;
import org.openide.WizardDescriptor;

/**
 *
 * @author Laszlo Kishalmi
 */
public class ServerSelectionPanelVisual extends javax.swing.JPanel {

    final J2eeModule.Type type;
    final DefaultComboBoxModel<Profile> profiles = new DefaultComboBoxModel<>();
    final List<ChangeListener> listeners = new LinkedList<>();

    /**
     * Creates new form ServerSelectionPanel
     */
    public ServerSelectionPanelVisual(J2eeModule.Type type) {
        this.type = type;
        initComponents();
        cbServer.setRenderer(new DisplayNameListCellRenderer<>(cbServer.getRenderer()));
        cbVersion.setRenderer(new DisplayNameListCellRenderer<>(cbVersion.getRenderer()));
        ComboBoxModel<J2eePlatform> model = JavaEEServerComboBoxModel.createJavaEEServerComboBoxModel(null, type, null);
        cbVersion.setModel(profiles);
        cbServer.setModel(model);
        if (model.getSize() > 0) {
            cbServer.setSelectedIndex(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbServer = new javax.swing.JLabel();
        cbServer = new javax.swing.JComboBox<>();
        btAddServerButton = new javax.swing.JButton();
        lbVersion = new javax.swing.JLabel();
        cbVersion = new javax.swing.JComboBox<>();

        lbServer.setLabelFor(cbServer);
        org.openide.awt.Mnemonics.setLocalizedText(lbServer, org.openide.util.NbBundle.getMessage(ServerSelectionPanelVisual.class, "ServerSelectionPanelVisual.lbServer.text")); // NOI18N

        cbServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbServerActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btAddServerButton, org.openide.util.NbBundle.getMessage(ServerSelectionPanelVisual.class, "ServerSelectionPanelVisual.btAddServerButton.text")); // NOI18N
        btAddServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddServerButtonActionPerformed(evt);
            }
        });

        lbVersion.setLabelFor(cbVersion);
        org.openide.awt.Mnemonics.setLocalizedText(lbVersion, org.openide.util.NbBundle.getMessage(ServerSelectionPanelVisual.class, "ServerSelectionPanelVisual.lbVersion.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbVersion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbServer, 0, 192, Short.MAX_VALUE)
                    .addComponent(cbVersion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAddServerButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbServer)
                    .addComponent(cbServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAddServerButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbVersion)
                    .addComponent(cbVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(232, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btAddServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddServerButtonActionPerformed
        String newServerId = ServerManager.showAddServerInstanceWizard();
        if (newServerId != null) {
            ComboBoxModel<J2eePlatform> model = JavaEEServerComboBoxModel.createJavaEEServerComboBoxModel(newServerId, type, null);
            cbServer.setModel(model);
            cbServerActionPerformed(null);
        }
        fireStateChange();
    }//GEN-LAST:event_btAddServerButtonActionPerformed

    private void cbServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbServerActionPerformed
        Object previousItem = cbVersion.getSelectedItem();
        profiles.removeAllElements();
        int selectedServer = cbServer.getSelectedIndex();
        if (selectedServer >= 0) {
            J2eePlatform platform = cbServer.getModel().getElementAt(selectedServer);
            if (platform != null) {
                Set<Profile> availableProfiles = new TreeSet<>(Profile.UI_COMPARATOR);
                for (Profile profile : platform.getSupportedProfiles(type)) {
                    if (type == J2eeModule.Type.WAR) {
                        if (profile == Profile.JAVA_EE_6_FULL || profile == Profile.JAVA_EE_7_FULL) {
                            continue;
                        }
                    } else {
                        if (profile == Profile.JAVA_EE_6_WEB || profile == Profile.JAVA_EE_7_WEB) {
                            continue;
                        }
                    }
                    if (profile == Profile.J2EE_13 || profile == Profile.J2EE_14) {
                        continue;
                    }
                    availableProfiles.add(profile);
                }
                for (Profile profile : availableProfiles) {
                    profiles.addElement(profile);
                }
                if (previousItem != null && availableProfiles.contains(previousItem)) {
                    cbVersion.setSelectedItem(previousItem);
                } else {
                    cbVersion.setSelectedIndex(0);
                }
            }
        }
    }//GEN-LAST:event_cbServerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddServerButton;
    private javax.swing.JComboBox<J2eePlatform> cbServer;
    private javax.swing.JComboBox<Profile> cbVersion;
    private javax.swing.JLabel lbServer;
    private javax.swing.JLabel lbVersion;
    // End of variables declaration//GEN-END:variables

    void read(WizardDescriptor settings) {
        String instanceId = (String) settings.getProperty(ServerSelectionPanel.PROP_SERVER);
        if (instanceId != null) {
            cbServer.setModel(JavaEEServerComboBoxModel.createJavaEEServerComboBoxModel(instanceId, type, null));
        }
        String profileId = (String) settings.getProperty(ServerSelectionPanel.PROP_PROFILE);
        if (profileId != null) {
            Profile profile = Profile.fromPropertiesString(profileId);
            cbVersion.setSelectedItem(profile);
        }
    }

    void write(WizardDescriptor settings) {
        if (cbVersion.getSelectedIndex() >= 0) {
            settings.putProperty(ServerSelectionPanel.PROP_PROFILE,
                    cbVersion.getItemAt(cbVersion.getSelectedIndex()).toPropertiesString());
        }
        if (cbServer.getSelectedIndex() >= 0) {
            String instanceId = JavaEEServerComboBoxModel.getServerInstanceID(cbServer.getItemAt(cbServer.getSelectedIndex()));
            settings.putProperty(ServerSelectionPanel.PROP_SERVER, instanceId);
        }
    }

    boolean isValidSelection() {
        return (cbVersion.getSelectedItem() != null)
                && (cbServer.getSelectedItem() != null);
    }

    public void fireStateChange() {
        ChangeEvent evt = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(evt);
        }
    }

    public void addChangeListener(ChangeListener l) {
        listeners.add(l);
    }

    public void removeChangeListener(ChangeListener l) {
        listeners.remove(l);
    }

}
