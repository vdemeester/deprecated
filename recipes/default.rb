#
# Cookbook Name:: gitolite
# Recipe:: default
#
# Author:: Vincent Demeester <vincent@demeester.fr>
#
# Copyright 2011, Vincent Demeester
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

require_recipe "git"

case node[:platform]
when "ubuntu", "debian"
    if node[:gitolite][:use_backport] and node.platform == "debian"
        # Testing if we are using squeeze… on sid backports are no available
        if node[:platform_version].to_f >= 6.0 and node[:platform_version] !~ /.*sid/
            # Add the squeeze-backports repository if not present
            apt_repository "backports" do
                uri "http://backports.debian.org/debian-backports"
                distribution "squeeze-backports"
                components ["main"]
                action :add
            end
            # Add a apt preference file to change the gitolite policy
            cookbook_file "/etc/apt/preferences.d/gitolite-squeeze-backports" do
                source "gitolite-squeeze-backports"
                mode 0755
                owner "root"
                group "root"
            end
        end
    end
    package "gitolite" do
        action :install
    end
when "arch"
    include_recipe "pacman"
    p = pacman_aur "gitolite-git" do
        action :nothing
    end
    p.run_action(:build)
    p.run_action(:install)
else
    # should catch any distro with gitolite as a package.
    package "gitolite" do
        action :install
    end
end
