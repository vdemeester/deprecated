# 
# Cookbook Name:: redmine
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

include_recipe "rails"
include_recipe "apache2"
include_recipe "apache2::mod_rewrite"
#include_recipe "passenger_apache2::mod_rails"

redmine_version = "redmine-#{node[:redmine][:version]}"

# Install/upgrade rake and libapache2-mod-passenger as we will need it some day
package "rake libapache2-mod-passenger" do
    action :upgrade
end

# Get redmine
remote_file "/opt/#{redmine_version}.tar.gz" do
    source "#{node[:redmine][:mirror]}/#{node[:redmine][:dl_id]}/#{redmine_version}.tar.gz"
    mode "0644"
    checksum "#{node[:redmine][:checksum]}"
    not_if {File.exists?("/opt/#{redmine_version}.tar.gz")}
end

execute "Extract redmine" do
    command <<-EOF
        tar xf /opt/#{redmine_version}.tar.gz -C /opt && \
chown -R #{node[:apache][:user]}:#{node[:apache][:group]} /opt/#{redmine_version}
    EOF
    not_if {File.directory?("/opt/#{redmine_version}")}
end

case node[:redmine][:db][:type]
when "sqlite"
    include_recipe "sqlite"
    package "libsqlite3-ruby" do
        action :upgrade
    end
    # gem_package "sqlite3-ruby"
    file "/opt/#{redmine_version}/db/production.db" do
        owner node[:apache][:user]
        group node[:apache][:group]
        mode "0644"
    end
when "mysql"
when "postgresql"
end

template "/opt/#{redmine_version}/config/database.yml" do
    source "database.yml.erb"
    owner "root"
    group "root"
    variables :database_server => node[:redmine][:db][:hostname]
    mode "0664"
end

# Generate session stuff (new installation and upgrades)
execute "rake generate_session_store" do
    user node[:apache][:user]
    cwd "/opt/#{redmine_version}"
end

# Migrate database (new installation and upgrades)
execute "rake db:migrate RAILS_ENV='production'" do
    user node[:apache][:user]
    cwd "/opt/#{redmine_version}"
    not_if {File.exists?("/opt/#{redmine_version}/db/schema.rb") }
end

# Load default data the first time only !
# Contains a *hack* for not failing when rdoc is not installed.
# FIXME maybe this *hack* should be somewhere else
execute "Initial load of data" do 
    command <<-EOF
        sed -i "1irequire 'thread'" config/boot.rb && \
rake redmine:load_default_data RAILS_ENV='production' REDMINE_LANG='#{node[:redmine][:lang]}'
    EOF
    user node[:apache][:user]
    cwd "/opt/#{redmine_version}"
    not_if {File.symlink?("/opt/redmine")}
end

# Linking redmine version to /opt/redmine (at the end)
link "/opt/redmine" do
    to "/opt/#{redmine_version}"
end

web_app "redmine" do
    docroot "/opt/redmine/public"
    template "redmine.conf.erb"
    server_name "redmine.#{node[:domain]}"
    server_aliases [ "redmine", node[:hostname] ]
    rails_env "production"
end
