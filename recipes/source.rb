# 
# Cookbook Name:: gitolite
# Recipe:: source
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

# Get gitolite and install it
bash 'install_gitolite' do
    user "root"
    cwd "/tmp/"
    code <<-EOF
        rm -fR gitolite
        git clone git://github.com/sitaramc/gitolite gitolite
        cd gitolite
        git checkout -t -f origin/pu
        mkdir -p /usr/local/share/gitolite/conf /usr/local/share/gitolite/hooks
        src/gl-system-install /usr/local/bin /usr/local/share/gitolite/conf /usr/local/share/gitolite/hooks
    EOF
    creates '/usr/local/bin/gl-setup'
end

# Create the user
user node[:gitolite][:username] do
    comment "#{node[:gitolite][:username]} gitolite user"
    home "/home/#{node[:gitolite][:username]}"
    shell "/bin/bash"
    # FIXME Use supports :create_home ?
    action :create
end
# And its folder
directory "/home/#{node[:gitolite][:username]}" do
    owner node[:gitolite][:username]
    action :create
end

# The admin ssh public key
file "/tmp/gitolite-#{node[:gitolite][:admin][:username]}.pub" do
    owner node[:gitolite][:username]
    content node[:gitolite][:admin][:key]
end
# gitolite_rc ?
template "/home/#{node[:gitolite][:username]}/.gitolite.rc" do
    owner node[:gitolite][:username]
    source "gitolite.rc.erb"
    action :create
end

# Installing gitolite in git
execute "install_gitolite" do
    user node[:gitolite][:username]
    command "/usr/local/bin/gl-setup /tmp/gitolite-#{node[:gitolite][:admin][:username]}.pub"
    environment ({'HOME' => "/home/#{node[:gitolite][:username]}"})
end

