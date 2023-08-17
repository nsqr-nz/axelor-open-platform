## 6.1.5 (2023-08-16)

#### Changes

* Meta json field precision for decimal field is now 20 by default

#### Fixed

* Fix label field title reset
* Fix static widgets causing editor dirty
* Fix setting/resetting widgets title

## 6.1.4 (2023-06-23)

#### Features

* Preserve grid scroll position on form save/reload

#### Fixed

* Fix buttons actions in Tree views
* Fix on how application and module are determinate during gradle resolution

  <details>
  
  Due to the merge of `com.axelor.app` and `com.axelor.app-module` gradle
  plugins, it is now hard to determinate who is the module from the
  application. A module can be built itself, so it is seen as an
  application (when checking `project == project.getRootProject()`) and
  wrong plugins/dependencies/tasks are applied.
  
  To overcomes this, when a module need to be built standalone,
  `axelor.application = false` property can be added in `gradle.properties`. 
  This way, it will be seen as a module instead of an application.
  
  Better support will be added in a future version.
  
  </details>

* Fix going into edit mode in editable grid when clicking readonly cell
* Fix selection widget stealing focus after focusing another cell
* Readonly fields, included dot fields, shouldn't be focusable
* Fix search request when adjusting page boundary
* Fix onChange on Enter key in simple fields
* Fix lost dotted fields in grid when using master-detail widget
* Fix editable grid that don't wait for pending actions
* Fix deselected row after save triggered by previous row in editable grid
* Fix search box show/hide on cards view dashlet depending on dashlet `canSearch` attribute
* Fix onNew action on editor
* Don't create webapp folder in war
* Fix toolbar buttons display when same grid is displayed multiple times

#### Security

* Check for unauthorized users inside security filter directly

## 6.1.3 (2023-05-15)

#### Fixed

* Align script helper test expressions with Action behavior
* Fix attributes that need a test instead of an evaluation
* Fix action test condition when context proxy is used
* Do not try to generate binary download link on unsaved record

## 6.1.2 (2023-04-05)

#### Changes

* Improve resolution of AOP core dependencies

  <details>
  
  Use AOP version defined in root project. This avoids to use a version
  coming from transitive dependencies.
  
  For example, if a module is built and published using AOP version 6.1.2
  and the root project use AOP version 6.1.1, it will now use the AOP
  version of the project, ie 6.1.1 (instead of getting the AOP version of
  the transitive dependency of the module).
  
  </details>

#### Fixed

* Don't reload dashlet custom view when the widget is not visible
* Disable exporting on Kanban views
* Fix empty recipients list when posting message or adding followers
* Fix onChange triggered after grid edit cancel
* Fix missing "refresh", "new", "prev", and "next" keyboard shortcuts on cards and kanban views
* Fix moving record on top level grid
* Fix grid not editable depending on readonly/canEdit conditions
* Fix redefined User namecolumn in collaboration widget

  <details>
  
  When the namecolumn of the User entity is redefined,
  it was not taken into account in the collaboration widget.
  
  </details>

* Fix editable grid preventing save

  <details>
  
  On slow network and/or big grids, going in and out of grid edit
  may trigger duplicate grid edit events and mess with the counting
  of active editable grids. This could cause saving to fail.
  
  </details>

* Fix export on relational fields
* Fix spinner buttons triggering onChange inside editable grid
* Escape data when generating xml
* Don't allow to post message without body

#### Security

* Check `canNew` view attribute with "create new record" keyboard shortcut

## 6.1.1 (2023-02-06)

#### Fixed

* Fix dirty view when an editor contain a button
* Don't set default value on dotted fields of existing records

  <details>
  
  This fixes values mismatch of dotted fields having default values after saving new record
  from form view and switching back to grid.
  
  </details>

* Fix merging of namecolumn fields in code generator
* Fix multiple grouping on grid
* Fix reloading meta on systems having high number of cores
* Fix onnew popup actions called with delay
* Fix  hidden panels/buttons in editor when the record changes
* Fix details from view attrs reset when reloading from grid/tab
* Fix auth provider setting `exclusive`
* Forbid adding init params

  <details>
  
  When merging properties, it should be forbidden to add any init params,
  whether we're overriding fields or adding fields.
  
  </details>

* Don't warn about unchanged ref when merging entity props in code generator

  <details>
  
  `ref` may be specified as simple name or fully qualified name.
  When one is using simple name, compare by simple name only,
  as to avoid spurious warning.
  
  </details>

* Fix tab refresh with HTML dashlet
* Fix grid grouping with evaluated scale

  <details>
  
  Fix grid grouping when there are fields using scale evaluation (`x-scale="field"`).
  Use the maximum scale in the group for the formatted aggregation.
  
  </details>


## 6.1.0 (2022-11-03)

#### Changes

* Change code generator strategy to merge item attributes

  <details>
  
  Previously, the code generator replace the field definition by the new one. Now 
  the code generator will merge initial field attributes with overridable attributes.
  So, only necessarily attributes that need to be override should be defined in the 
  overwritten entity.
  
  This applies to both entities and enums.
  
  In the case of entity fields, there are a few restrictions:
    * Attributes that are not overridable:
      * `initParam`
      * `column`
      * `column2`
      * `ref`
      * `mappedBy`
      * `table`
      * `tz`
      * `json`
    * Attributes that are overridable with some conditions:
      * `large`: large field cannot become non-large
      * `transient` and `formula`: persisted field cannot become non-persisted
  
  </details>

* Remove `auth.provider.xx.absoluteUrlRequired` property
* Allow setting min/max to blank in order to remove the attribute in code generator
* Define maximum number of records per page

  <details>
  
  This change the default `api.pagination.max-per-page`, currently allowing unlimited 
  number of records per page, to 500.
  
  </details>

* Admins group can now customize views by default
* Disable sorting on grids having `canMove="true"`
* Let JpaFixture items persist error propagate

  <details>
  
  JpaFixture is used for tests.
  
  In case of data error, tests failed without the actual cause, and there is no point in continuing
  and have obscure errors happen on wrong data.
  
  </details>

* Support of `X-Forwarded-Context` header removed in favor of `X-Forwarded-Prefix`
* Reorder HTML widget buttons for consistency with Markdown widget
* Reorder toolbar icons for consistency with Markdown widget
* Move JpaFixture to axelor-test module
* Upgrade HSQL JDBC from 2.6.1 to 2.7.0
* Upgrade MySQL JDBC from 8.0.29 to 8.0.30
* Upgrade UnboundID LDAP SDK from 6.0.5 to 6.0.6
* Upgrade Junit from 5.8.2 to 5.9.1
* Upgrade Upgrade embedded Tomcat from 9.0.63 to 9.0.65
* Upgrade StringTemplate from 4.3.3 to 4.3.4
* Upgrade Greenmail from 1.6.9 to 1.6.10
* Upgrade Redisson from 3.17.3 to 3.17.6
* Upgrade Resteasy from 4.7.6 to 4.7.7
* Upgrade Groovy from 3.0.10 to 3.0.13
* Upgrade Flyway from 8.5.11 to 9.3.1
* Upgrade Hibernate Validator from 6.2.3 to 6.2.4
* Upgrade Woodstox from 6.2.8 to 6.3.1
* Upgrade Undertow from 2.2.17 to 2.2.19
* Upgrade EclipseLink MOXy from 2.7.10 to 2.7.11
* Upgrade Shiro from 1.9.0 to 1.9.1
* Upgrade Spotless from 6.5.1 to 6.11.0
* Upgrade Jsoup from 1.14.3 to 1.15.3
* Upgrade Pac4j from 5.4.3 to 5.4.5
* Upgrade Caffeine from 3.1.0 to 3.1.1
* Upgrade Hazelcast from 5.1.1 to 5.1.3
* Upgrade Jackson from 2.13.3 to 2.13.4
* Upgrade Infinispan from 13.0.10 to 13.0.11
* Upgrade PostgreSQL JDBC from 42.3.6 to 42.5.0
* Upgrade Tika from 2.3.0 to 2.4.1
* Upgrade Byte Buddy from 1.12.10 to 1.12.17
* Upgrade Ehcache from 3.10.0 to 3.10.1
* Upgrade Gradle from 7.4.2 to 7.5.1
* Upgrade Gradle Node Plugin from 3.2.1 to 3.4.0

#### Features

* Improve support of `X-Forwarded-*` headers

  <details>
  
  App now have full support for `X-Forwarded-*` headers and provides
  better usage of proxy management.
  
  Supported headers are: `X-Forwarded-Host`, `X-Forwarded-Port`, 
  `X-Forwarded-Proto`, `X-Forwarded-Prefix`, `X-Forwarded-For`.
  
  pac4j usage is now based on the current request. When redirecting urls, 
  the Location is now absolute and no more relative to current servlet path.
  This avoids custom proxy configuration to rewrite the location or cookie path.
  
  </details>

* Implement setting scale on grid column by action

  <details>
  
  `scale` attribute on a decimal field can be change on a grid column by an action.
  
  ```xml
  <action-attrs name="action-set-scale">
    <attribute for="items.price" name="scale" expr="eval: 10"/>
  </action-attrs>
  
  <form ...>
    ...
    <panel-related field="items">
      ...
      <field name="price"/>
    </panel-related>
    ...
  </form>
  ```
  
  </details>

* Displays the number of displayed and totaled items on the DMS list view
* Use colored letter on top right corner as placeholder user icon

  <details>
  
  This aligns user display with mail message and collaboration.
  
  </details>

* Implement Markdown widget using TOAST UI Editor with Code Syntax Highlight Plugin

  <details>
  
  Example:
  
  ```xml
  <field name="myTextField" widget="markdown"/>
  ```
  
  | Attribute           | Description                                                    |
  | ------------------- | -------------------------------------------------------------- |
  |`x-lite`             | Enable lite toolbar (defaults to `false`)                      |
  |`x-preview-style`    | Markdown editor's preview style: `tab` (default), `vertical`   |
  |`x-initial-edit-type`| Initial editor type: `markdown` (default), `wysiwyg`           |
  |`x-hide-mode-switch` | Whether to hide edit typo switch tab bar (defaults to `false`) |
  
  </details>

* Fall back to colored letter user image in mail message thread in case of permission failure
* Allow to get value of field with selection in string templates

  <details>
  
  For example, use `<SaleOrder.statusSelect.value>` to get the value of the selection.
  With `<SaleOrder.statusSelect>`, you still get the title of the selection.
  
  </details>

* Support x-field attribute with InfoButton widget to specify the bound field

  <details>
  
  Example:
  
  Use the `x-field` attribute on `info-button` widget to specify the bound field. When using `x-field`, 
  the button and the field are 2 distinct elements. Any attributes defined on that field will be used to 
  format the value. Moreover, this allows to change the button attributes without impact on the bound field.
  
  ```xml
  <panel>
    <button name="amountBtn" title="Amount" widget="info-button" x-field="totalAmount" onClick="my-action"/>
    <field name="amount" hidden="true"/>
  </panel>
  ```
  
  </details>

* Dynamically evaluate x-scale from context

  <details>
  
  On a decimal field, `x-scale` attribute accept an field name for a dynamic evaluation.
  Grid and form view are both supported.
  
  ```xml
  <field name="decimalField" widget="Decimal" x-scale="currency.decimalPlaces" x-precision="18"/>
  ```
  
  </details>

* Implement support to see users on same view in realtime

  <details>
  
  This allows to see users that are seeing/editing/updating the current opened record.
  
  Feature can be disabled with `view.collaboration.enabled` property. By default, it is enabled.
  On groups, there is a new boolean `canViewCollaboration` to determine whether members
  can view collaboration (`true` by default).
  
  </details>

* Introduce default number of items displayed per page config

  <details>
  
  Introduce new config `api.pagination.default-per-page` :
  
  ```
  # Define the default number of items per page
  api.pagination.default-per-page = 40
  ```
  
  This config is used in UI, especially in grid views, to define the default number of items 
  displayed per page. Default value is still 40 records.
  
  </details>

* Allow code generator to merge transient and multirelational fields

  <details>
  
  Previously it was not able to override any attributes of transients and collections fields.
  Now it is allow to change some of their attributes.
  
  </details>

* Implement client-side sorting of o2m/m2m grids

  <details>
  
  When there were some pending changes on a grid, sorting used to be disabled.
  This is no longer the case thanks to client-side sorting.
  Also transient/dummy fields can now be sorted.
  Only o2m/m2m grids can be sorted client-side.
  Other grid views that use pagination still send search request upon sorting.
  
  </details>

#### Fixed

* Fix truth value of action test expressions

  <details>
  
  When evaluating action test expressions, "expr" should have the same truth value as "!!expr".
  
  It was working with types boolean, integer, date, time, datetime, enum, references (any-to-one),
  but was failing with long, decimal, string, binary, collections (any-to-many).
  
  </details>

* Fix x-scale="0"
* Fix blank m2o in grid when there is no namecolumn

## 6.0.4 (2022-11-02)

#### Changes

* Upgrade Hibernate from 5.6.9 to 5.6.12

  <details>
  
  This fixes null mapped one-to-one field on an entity loaded from L2 cache.
  
  </details>

#### Features

* Update meta field labels and descriptions when restoring meta models

  <details>
  
  Update meta field labels so that they can be used in grid customization.
  
  </details>

* Improve details-view usage

  <details>
  
  In a details-view, the form view can be closed on demand or if there is no selected 
  line. When we delete a line in the grid, the record in the form is no more displayed.
  
  This also fix duplicated requests, missing onLoad/onNew calls in some conditions and 
  reset attrs states on form view.
  
  </details>

#### Fixed

* Preserve scroll position after saving form in details view
* Fix grouped grid display when already initialized
* Fix chart context evaluation when calling from action-view
* Fix page on grid customization popup when containing extra fields
* Make sure grids have committed before firing button action

  <details>
  
  Fixes application that can become unresponsive after clicking on a button on a form view
  while having uncommitted editable grids.
  
  </details>

* Fix record pager display issue in popup
* Fix missing cell css applied on item's parent
* Merge search-filter fields title with the model field title in grid customization popup
* Fix query fetching missing fields in TagSelect widget
* Fix delete metafile if target file is not found
* Fix dependency conflict between GraalJS and Birt with package "com.ibm.icu"
* Fix adding duplicate dotted field in grid customization

  <details>
  
  Fix adding duplicate dotted field in grid customization
  when it exists both in view and search filters.
  
  </details>

* Fix customize dashboard with drag & drop
* Fix wrong number of attachments displayed on record toolbar paper clip icon

## 6.0.3 (2022-09-28)

#### Features

* Add view action to help popover

#### Fixed

* Fix missing item context after creating M2M item from popup
* Check for `view.allow-customization` setting when saving customized view
* Fix save action on top grid preventing further actions
* Fix wrong css class applied on editable grid
* Fix custom fields on editable grid
* Fix restarting jobs after scheduler shutdown
* Fix model class resolution in Groovy expressions
* Fix refreshing html dashlet when record changes
* Fix non editable grid view customization popup
* Fix required field using TagSelect widget
* Skip default values of dotted field in editable grid
* Fix timezone issues with date adapter

  <details>
  
  When the server is running on UTC- timezone, date could be converted back by one day.
  
  </details>

* Fix conflicting `Order` title
* Fix saving boolean false filter

  <details>
  
  In the case of boolean fields with operator `false`,
  filter was transformed in order to check for null or false.
  But by doing that, original criteria was lost, breaking meta filter saving.
  Replaced client-side operators `true` and `false` by new virtual operators
  `$isTrue` and `$isFalse` which perform client-side transformation.
  
  </details>

* Fix ReferenceError with "<=" operator on custom date/datetime fields
* Fix sidebar toggle on window resize
* Fix client authentication using path based callback url

  <details>
  
  Some clients, ie AzureAd2Client, use path based callback url (/callback/AzureAd2Client) instead of default query based callback url (/callback?client_name=AzureAd2Client).
  
  </details>

* Fix missing TagSelect placeholder until we add and remove an item
* Fix auth properties that exist in client and configuration

  <details>
  
  When a property exists in the configuration and the client, try to set both.
  
  For example, `scope` exists in both `GenericOAuth20Client` and `OAuthConfiguration`.
  
  </details>

* Fix clipped field in Modern theme when using `css="large"` on WebKit-based browsers
* Apply client-side operators on export and masss update

  <details>
  
  Applying client-side operators was done on search only.
  Now, it is also done on exports and mass updates.
  
  </details>

* Fix setting $-prefixed dummy fields from onNew with default values

  <details>
  
  Issue happened when setting $-prefixed dummy fields from onNew
  and record has some default values.
  
  </details>

* Fix wrong selected rows after grid sorting
* Fix custom date/datetime criteria processing combined with column search
* Use access token to retrieve user profile picture from OpenID Connect

  <details>
  
  This fixes retrieving user profile picture from Azure Active Directory,
  where sending the access token is required.
  
  </details>

* Fix refreshing html view from tab right click
* Fix save action with only default values

  <details>
  
  This allow to save a record that contain default values (generally coming from domain definition) 
  when calling `save` action. This behavior will be same as the toolbar save button behavior.
  
  </details>

* Fix changing dashlet url from `action-attrs`
* Fix setting authentication map property

  <details>
  
  This fixes setting `GenericOAuth20Client.profileAttrs`.
  Issue happened when there was no getter.
  
  ```properties
  # profile attributes: map of key: type|tag
  # supported types: Integer, Boolean, Color, Gender, Locale, Long, URI, String (default)
  auth.provider.oauth.profile-attrs.age = Integer|age
  auth.provider.oauth.profile-attrs.is_admin = Boolean|is_admin
  ```
  
  </details>


## 6.0.2 (2022-08-03)

#### Features

* Improve report-box template in custom view

  <details>
  
  * Implement icon attribute
  * Format value
  * Translate label
  * Implement dynamic percent style
  * Implement dynamic percent level style
  * Fix tag positioning
  
  </details>

* Add config to define the maximum number of items displayed per page.

  <details>
  
  Introduce new config `api.pagination.max-per-page` :
  
  ```
  # Define the maximum number of items per page
  api.pagination.max-per-page = 1000
  ```
  
  This config is used globally in UI to limit and set the maximum number of items 
  displayed per page. -1 means unlimited (default value). This will block users who 
  try to get a high number of records. Fetch a too large dataset can result in 
  some high server side load.
  
  </details>

* Minor fields tracking UI improvements

  <details>
  
  Add minor fields tracking UI improvements : 
  
  - Use right arrow character instead of right angle quote
  - Display `None` instead of an empty value
  - On create event, don't generate tracking on empty field
  
  </details>

* Improve `report-table` custom view

  <details>
  
  * Translate titles
  * Use title attribute from fields
  * Preserve column declaration order from dataset if report-table columns
  attribute is not specified
  * Format data according to field type, including selection with translation
  * Support field translatable attribute
  * Sticky header and footer
  * Skip having to define data='data'
  * Ability to sort by columns
  * Ability to use widgets supported in grid views
  * Allow to export dataset from dashlet export button
  
  Example:
  ```xml
  <custom name="my-report-order-lines" title="Order lines">
    <field name="statusSelect" type="integer" selection="selection-order-status" title="Status"/>
    <field name="product" type="string" x-translatable="true"/>
    <field name="total" type="decimal" x-scale="2" />
    <dataset type="jpql" limit="10">
    <![CDATA[
    SELECT self.name AS name, self.statusSelect AS statusSelect,
           item.product.name as product, item.quantity * item.price AS total
    FROM Order self
    LEFT JOIN self.customer AS c
    LEFT JOIN self.items AS item
    WHERE c = :customer
    ORDER BY self.name
    ]]>
    </dataset>
    <template>
    <![CDATA[
    <report-table sums='total'></report-table>
    ]]>
    </template>
  </custom>
  ```
  
  </details>

#### Fixed

* Fix chart dashlet data export
* Fix unregistered request scope causing database task to fail

  <details>
  
  If application has any bound RequestScoped classes, it will cause the database task to fail
  to install AppModule with "No scope is bound to com.google.inject.servlet.RequestScoped." error.
  
  </details>

* Fix advanced search with custom any-to-many fields
* Fix html view height in popup
* Ignore forceEdit if user has no write permission
* Fix TypeError when user has insufficient permission to display a panel tab
* Fix duplicate data loading of kanban dashlet inside form view
* Fix context of kanban in dashlet

  <details>
  
  Apply the same context behavior as other dashlets,
  ie. merge action-view context with current record context.
  
  Instead of domain, use criteria (as it is done for calendars) for the `columnBy` filter,
  as to avoid conflict with current record context.
  
  </details>

* Fix reload action on cards and kanban view
* Fix grid JS error if action sent a list of IDs instead of a list of records
* Don’t update the number value on spin events

  <details>
  
  This fixes action iconsistencies between changing number value via manual input and spinner.
  
  </details>

* Take into account view-param limit on kanban views
* Fix spurious invalid fields notice when master detail is not shown
* Fix adding row on top editable grid when editor buttons are disabled
* Prevent popup from handling editable grid key down events

  <details>
  
  This fixes popup closing when pressing escape in editable grid.
  
  </details>

* Fix the view opened on mail thread creator link

  <details>
  
  The `user-info-form` view can be bypassed if we refresh the browser tab after 
  clicking on mail thread creator. The view is now opened in popup. If no view 
  is defined, nothing is opened (no more fallback on `user-form`).
  
  </details>

* Fix grid column search combined with predefined and custom search filters
* Don’t check for CSRF tokens with direct clients
* Don't reload dashlet calendar data when the widget is not visible
* Fix adding row on grouped editable grid
* Fix array of strings in context

  <details>
  
  For example:
  
  ```xml
    <context name="_myStrings" expr="eval: ['hello', 'world']"/>
  ```
  
  </details>

* Commit editable grid when closing popup without confirming

  <details>
  
  In a popup containing an editable grid, the line being edited was not committed if we directly
  close the popup via the "OK" button without clicking on "Confirm" in the editable grid.
  
  </details>

* Fix missing group on views and menus when loading
* Fix emptying a field using SingleSelect widget
* Fix uninitialized injector when running database task
* Fix broken translation popup
* Fix exporting selection field if multi-select widget is used

## 6.0.1 (2022-06-27)

#### Changes

* Upgrade embedded Tomcat from 9.0.62 to 9.0.63
* Upgrade Hibernate ORM from 5.6.8.Final to 5.6.9.Final
* Upgrade Byte Buddy from 1.12.9 to 1.12.10
* Upgrade Infinispan from 13.0.9 to 13.0.10
* Upgrade Redisson from 3.17.1 to 3.17.3
* Upgrade PostgreSQL JDBC from 42.3.4 to 42.3.6
* Upgrade Flyway from 8.5.10 to 8.5.11
* Upgrade Jackson from 2.13.2 to 2.13.3
* Upgrade UnboundID LDAP SDK from 6.0.4 to 6.0.5
* Rework mail message endpoints

  <details>
  
  Some mail message endpoints has been reworked to use dedicated path. This avoid to give permission 
  to `MailMessage` or `MailFlag` objects in order to access the mail menus and deal with messages and flags.
  `MailMessage` action-view is now always allowed in order to open mail views.
  
  </details>

* Upgrade GreenMail from 1.6.8 to 1.6.9

#### Features

* Update file name format for duplicate file name

  <details>
  
  Update format from `file (1).txt` to `file-1.txt`. This avoids 
  necessary space in file name.
  
  </details>

* Show configured application logo in about page
* Report duplicate view items at the end of the view loading process
* Sanitize uploaded filenames

  <details>
  
  Sanitize uploaded filenames :
  - Removes special characters that are illegal in filenames on certain operating systems
  - Replaces spaces and consecutive underscore with a single dash
  - Trims dot, dash and underscore from beginning and end of filename
  
  </details>

#### Fixed

* Don’t save meta filters with spurious `$new` attribute
* Fix saving filter with string field

  <details>
  
  In the case of string fields with operators `isNull` and `notNull`,
  filter was transformed in order to check for null or empty.
  But by doing that, original criteria was lost, breaking meta filter saving.
  
  Instead, use new virtual operators `$isEmpty` and `$notEmpty`
  which perform client-side transformation.
  
  </details>

* Fix MetaModule#application flag not filled
* Take column filters into account when refreshing dashlets
* Fix calendar dashlet refresh after refreshing/saving form view
* Fix mail thread avatar img display
* Fix downloaded filename from html action-view
* Fix uploading file if a file with the same name exist
* Fix missing name and color fields from selector with TagSelect widget
* Fix missing `_domainAction` from context of kanban in dashlet

  <details>
  
  The attribute `_domainAction` is needed to reevaluate the context server-side.
  
  </details>

* Fix ignored canNew attribute and permissions when pressing enter on last row of editable grid
* Fix deadlock when loading views of different types with same 'id'
* Fix meta file not found error
* Fix downloaded filename encoding in Content-Disposition header
* Clear dashlet filters when changing to another record
* Allow users to paste into password fields on change password form

  <details>
  
  Preventing password pasting undermines good security policy.
  https://web.dev/password-inputs-can-be-pasted-into/?utm_source=lighthouse&utm_medium=devtools
  
  </details>

* Fix duplicate data loading for grid dashlets on dashboards
* Fix closing of resources in CSV/XML importers
* Fix chart onAction and onClick having no context

  <details>
  
  onAction and onClick should have context just like onInit.
  
  </details>

* Fix keyboard shortcut popup content
* Fix grouping by extra custom field in grid view

  <details>
  
  Fix grouping by custom field defined on JSON field other than default `attrs`.
  
  </details>

* Fix too many redirects error in case of missing pac4j user profile
* Fix missing `_domainAction` from context of calendar in dashlet

  <details>
  
  The attribute `_domainAction` is needed to reevaluate the context server-side.
  
  </details>

* Fix hide issue on archive/attach/log grid built-in toolbar buttons

  <details>
  
  `hidden` attribute on built-in toolbar buttons wasn't taken into account for 
  archive/attach/log buttons.
  
  For example:
  ```xml
  <toolbar>
    <button name="log" onClick=" " hidden="true"/> <-- Hide audit log button -->
    <button name="attach" onClick=" " hidden="true"/> <-- Hide attachment button -->
    <button name="archive" onClick=" " hidden="true"/> <-- Hide archive/unarchive button -->
  </toolbar>
  ```
  
  </details>


## 6.0.0 (2022-05-20)

#### Changes

* Re-implement action-ws using http client
* Update join table columns names due to database reserved words conflicts

  <details>
  
  Due to reserved words added in recent versions of supported databases, change columns name `groups` and `menus` 
  to respectively `group_id` and `meta_menu_id` in the join tables name `meta_menu_groups` and `meta_view_groups`.
  
  Use these SQL statements to upgrade existing database :
  ```sql
  ALTER TABLE meta_menu_groups RENAME COLUMN groups TO group_id;
  ALTER TABLE meta_menu_groups RENAME COLUMN menus TO meta_menu_id;
  ALTER TABLE meta_view_groups RENAME COLUMN groups TO group_id;
  ALTER TABLE meta_view_groups RENAME COLUMN views TO meta_view_id;
  ```
  
  </details>

* Upgrade from Guice 4.2.2 to 5.1.0
* Remove `com.axelor.event.Priority` in favor of `javax.annotation.Priority`
* Not allowed to customize the search engine grid
* Upgrade to Hibernate Search 5.11.10.Final
* Search engine improvements

  <details>
  
  The search engine view has been improved. It allows to implement recursive filters for more flexibility on the search condition,
  but also:
  * Add support for `Enum` field type in search fields.
  * Allow to define `limit` on each `select`. It gets preference over `limit` attribute of `search`.
  * Add support for `enum` and `selection` (also autodetect `multi-select` values) for fields in result fields.
  * Add support for returning only distinct records (based on `id`) on each `select`.
  * Allow to add an `if` condition on `where`. It the value of the expression is false, the element is skipped.
  * Add support for hiliting on row as well as on field.
  * Add support for adding buttons in grid view.
  
  Breakings changes:
  * The `orderBy` fields should refere to field names of the object graph (and no more the `as` attribute).
  * When searching on a multi-valued field (O2M/M2M), it shouldn't need to be suffixed with [] anymore. For example,
  `items[].product` should now be `items.product`.
  
  </details>

* Improve groups and jobs `data-init`

  <details>
  
  The default `admins` group is now marked as technicalStaff. The default
  `mail.fetcher` job is no more active by default.
  
  </details>

* Upgrade to StringTemplate 4.3.3
* Format numbers, dates, and currencies preferably according to the user language

  <details>
  
  Formatting of numbers, dates, and currencies used to be based on browser locale only.
  Now, formatting is done preferably according to the user language.
  If the user language has no country information, we select the first browser locale
  that matches the user language.
  
  </details>

* Upgrade to Guava 31.1-jre
* Rename `application.properties` to `axelor-config.properties`

  <details>
  
  The internal configuration file `application.properties` is renamed to `axelor-config.properties`
  
  </details>

* Upgrade from JDK 8 to JDK 11

  <details>
  
  https://docs.oracle.com/en/java/javase/11/migrate/index.html#JSMIG-GUID-7744EF96-5899-4FB2-B34E-86D49B2E89B6
  
  </details>

* Upgrade to Spotless 6.5.1
* Use jcache in combination with Caffeine as second-level cache provider

  <details>
  
  Ehcache2 second-level cache is deprecated since Hibernate 5.3.
  Use `jcache` in combination with Caffeine by default as second-level cache.
  
  </details>

* Rework actions on chart menu

  <details>
  
  Adding buttons on chart menu was working using the following syntax :
  `<config name="onAction" value="some-action"/>`
  
  This syntax has been updated to the following :
  ```
  <actions>
  <action name="myBtn1" title="My action 1" action="some-action1"/>
  <action name="myBtn2" title="My action 2" action="some-action2"/>
  </actions>
  ```
  
  This allows to support more than 1 button on chart menu, but also
  provide a flexible usage of the feature.
  
  </details>

* Upgrade PostgreSQL Jdbc to 42.3.4

  <details>
  
  This add support new `SCRAM-SHA-256` password encryption method which is  
  now the default password encryption method is PostgreSQL 14.
  
  Jdbc also introduce a change about Timestamp rouding : 2018-06-03T23:59:59.999999999 is rounded to 
  2018-06-04 00:00:00 (previously it was rounded to 2022-03-08 23:59:59.999). This new behavior follow psql’s suit.
  Especially if you work with Datetime/Timestamp having `LocalTime.MAX` as time, make sure to adjust your query.
  
  </details>

* Adopt a better configurations naming

  <details>
  
  A lot of configurations names has been updated to have better 
  and uniform naming across all settings. Refer to migration notes.
  
  </details>

* Support indirect/direct basic auth client via setting `auth.local.basic-auth`

  <details>
  
  Indirect basic auth allows to log in on callback, while direct basic auth requires credentials in each request.
  
  ```properties
  # enable indirect and/or direct basic auth
  auth.local.basic-auth = indirect, direct
  ```
  
  `auth.local.basic.auth.enabled`, which was for direct basic auth only, is removed.
  
  </details>

* Upgrade to Hibernate 5.6.8.Final

  <details>
  
  Most notably, positional parameters for native queries are now one-based.
  
  </details>

* Upgrade to intl-tel-input 17.0.16
* Upgrade to Gradle 7.4.2
* Fire PreRequest and PostRequest events outside of transactions

  <details>
  
  `PreRequest`/`PostRequest` events are now fired outside of transactions.
  This fixes accessing the created/update records in a multithreaded process from `PostRequest` observers.
  However you can no longer rollback the request process in a `PostRequest` observer.
  
  </details>

* Upgrade to Apache Tomcat® 9.x
* Upgrade to pac4j 5.4.3
* Reimplement code generator in Java

  <details>
  
  Dropped old code generator written in Groovy in favor of a new code generator written in Java.
  
  </details>

* Upgrade JUnit4 from 4.12 to 4.13.2
* Upgrade from Groovy 2.4.10 to 3.0.10

  <details>
  
  Beware of breaking changes.
  See release note for more details :
  - https://groovy-lang.org/releasenotes/groovy-2.5.html
  - https://groovy-lang.org/releasenotes/groovy-2.6.html
  - https://groovy-lang.org/releasenotes/groovy-3.0.html
  
  </details>

* Merge `com.axelor.app` and `com.axelor.app-module` gradle plugins

  <details>
  
  Now there is only a single `com.axelor.app` plugin. All modules
  should use the app plugin only.
  
  Now all axelor modules are axelor apps.
  
  </details>

* Upgrade to Hibernate Validator 6.2.3.Final
* Default gantt view mode is now `month` instead of `week`

  <details>
  
  This can be changed on `<gantt>` view definition using the 
  `mode` attribute.
  
  </details>

* Upgrade to Shiro 1.9.0
* Replace nashorn script helper with GraalJS engine

  <details>
  
  The nashorn script engine is deprecated in JDK-11 and has some
  incompatible changes than JDK-8.
  
  The new implementation uses GraalJS which supports latest ECMAScript features.
  
  The collection helpers `listOf`, `setOf` and `mapOf` are removed as corresponding
  native JavaScript objects are passed with appropriate Java equivalent wrapper to the
  Java calls.
  
  </details>

* Rename method `getCodeOrEmail` to `getUserIdentifier` in `AuthPac4jProfileService`

  <details>
  
  Use a more generic method name, because users may override and use custom behavior.
  
  </details>

* Rework authentication implementation to use reflection and providers

  <details>
  
  Reflection is now used to configure authentication clients.
  The new syntax is `auth.provider.<providerName>.<configurationName>`.
  You may use any of the built-in providers: `google`, `facebook`, `github`, `azure`, `keycloak`,
  `apple`, `oauth`, `oidc`, `saml`, `cas`.
  Or you can configure any other clients supported by pac4j using your own custom provider name.
  You may even create and use your own custom authentication clients.
  
  </details>

* Upgrade to Quartz 2.3.2

#### Features

* Allow to customize notify/info/alert/error message modal/notification

  <details>
  
  This allow to add more customization on modal/notification : 
  - `notify` : allow to change the title of the notification
  - `info` : allow to change the title of the popup and the title of the confirm button
  - `alert` : allow to change the title of the popup and the title of the confirm and cancel button
  - `error` : allow to change the title of the popup and the title of the confirm button
  
  Example usage:
  
  ```xml
  <action-validate name="my-action">
    <notify message="A notification" title="My notif"/>
    <info message="This is an info" title="My info" confirm-btn-title="Got it"/>
    <alert message="This is an alert" title="My alert" confirm-btn-title="Got it" cancel-btn-title="Abort"/>
    <error message="This is an error" title="My error" confirm-btn-title="I understand" cancel-btn-title="Do something else"/>
  </action-validate>
  ```
  
  or with `ActionResponse`:
  
  ```java
    response.setNotify("A notification", "My notif");
    response.setInfo("This is an info", "My info", "Got it");
    response.setAlert("This is an alert", "My alert", "Got it");
    response.setError("This is an error", "My error", "I understand", "Do something else");
  ```
  
  </details>

* Parallelize generation of computed views
* Use Gradle Node Plugin to run npm tasks

  <details>
  
  No more need to install Node.js locally to build web resource bundles, aka `npm-build` task.
  
  If your project already depends on `gradle-node-plugin`, there will be conflicts between both. As the provided plugin 
  is only applied on root project, the best fit is to delegate the process to a subproject. That way, the subproject 
  can process and build your needs, and the root project can depends on that subproject and gets produced build.
  
  To use it, you need to Declare Node.js repository in `settings.gradle`, section `dependencyResolutionManagement` : 
  ```gradle
    // Declare the Node.js download repository
    ivy {
      name = "Node.js"
      setUrl("https://nodejs.org/dist/")
      patternLayout {
        artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]")
      }
      metadataSources {
        artifact()
      }
      content {
        includeModule("org.nodejs", "node")
      }
    }
  ```
  
  See https://github.com/node-gradle/gradle-node-plugin/blob/3.1.1/docs/faq.md#is-this-plugin-compatible-with-centralized-repositories-declaration
  
  </details>

* Implement WebSocket support
* Add support to encrypt secrets in properties

  <details>
  
  Secrets defined in `axelor-config.properties` can be encrypted. 
  Value should be wrapped in `ENC()` to indicate that the value is
  encrypted : `db.default.password = ENC(<some_thing>)`
  
  To use encrypted secrets, `config.encryptor.password` properties
  should be added : this is the secret key used to encrypt/decrypt data.
  
  Others optional properties can be added to use custom encryption : 
  `config.encryptor.algorithm`, `config.encryptor.key-obtention-iterations`, 
  `config.encryptor.provider-name`, `config.encryptor.provider-class-name`, 
  `config.encryptor.salt-generator-classname`, `config.encryptor.iv-generator-classname`, 
  and `config.encryptor.string-output-type`.
  The default algorithm is `PBEWITHHMACSHA512ANDAES_256`.
  
  For convenience, a Gradle task `encryptText` have been added to generate 
  the encrypted value of a string :
  `./gradlew :encryptText --text="A secret to encode" --password="MySecureKey"`
  This will generate for you, the necessary properties and the 
  encrypted value to used inside `ENC()`.
  
  </details>

* Add application setting `auth.order` to set authentication provider order

  <details>
  
  Because of multisource application settings, settings order is undefined.
  Use `auth.order` if you require authentication providers to be displayed in a specific order
  on login page.
  
  </details>

* Add JpaRepository#findByIds(List<Long>) method to find multiple entities by their primary key
* Add support to MySQL 8
* Allow closing preference view manually with `canClose` or `close` action
* Add validation support for csv import

  <details>
  
  Two new attributes added to validate data being imported.
  
  - `check` - boolean expression
  - `check-message` - the validation message
  
  If `check` fails, the `check-message` or default validation message
  is shown and import will be terminated.
  
  </details>

* Add Gantt view attribute `mode`

  <details>
  
  Gantt view attribute `mode` allows to define the default view mode.
  It can be set to `year`, `month`, `week`, or `day`.
  
  </details>

* Add some predefinied css classes for dialogs and notify components
* Sanitize HTML where it is needed with DOMPurify

  <details>
  
  Instead of sanitizing all jQuery.html calls, use DOMPurify to sanitize HTML where it is needed only.
  This should reduce scripting load in the web client.
  
  </details>

* Perform accent-insensitive navigation search
* Refactoring menu and tags processing

  <details>
  
  This is a refactoring on how menus and tags are processing. The original way was complex and mixing tags 
  and menus rules in a single method. There is no changes on how it works.
  
  Now, menus and tags processing are separate. Moreover, instead of parsing each menu one by one (but also recursively 
  checking parent access), it builds a tree. The tree is a representation of the menus' hierarchy, where each menu have 
  a parent and some child's. Then the tree is traversed, checking each node access. When a node is allowed, it 
  continues traversing each child's. When a node is not allowed, it skips the node and all the child's.
  
  </details>

* Support YAML configuration format, env variables and system properties

  <details>
  
  Now support YAML format for internal configuration file :
  `axelor-config.properties` can be in YAML format (`yml` or `yaml` ext).
  It should only have a one internal configuration file (in properties
  or YAML format).
  
  External configuration file can be provided by using system properties
  (`axelor.config=<path_to_file>`) or using env variables
  (`AXELOR_CONFIG=<path_to_file>`). Same as the internal configuration
  file, it also supports YAML format.
  
  The internal configuration file is now optional. Final properties
  are built using internal configuration file + external configuration
  file + env variable + system properties. If variables are redefined,
  they will take preferences over the previous values.
  
  Configuration values can also be provided with system properties
  using `axelor.config.<key>=value` format. For example
  `db.default.user` becomes `axelor.config.db.default.user`.
  
  Configuration values can also be provided with environment variables
  using `AXELOR_CONFIG_<key>=value` format, where `<key>` is underscored
  uppercase equivalent of the configuration key. For example
  `db.default.user` becomes `AXELOR_CONFIG_DB_DEFAULT_USER`.
  
  </details>

* Add `QuickMenu` to allow running actions from default page

  <details>
  
  Example usage:
  
  ```java
  public class MyQuickMenu implements QuickMenuCreator {
  
    @Override
    public QuickMenu create() {
      final QuickMenu menu = new QuickMenu();
      menu.setTitle("My menu");
      menu.setOrder(0);
      menu.setShowingSelected(false);
      menu.setItems(
          List.of(
              new QuickMenu.Item("All projects", "project.all"),
              new QuickMenu.Item("All tasks", "project.task.all")));
      return menu;
    }
  }
  ```
  
  and register the QuickMenu in module configuration : 
  
  ```java
  public class MyModule extends AxelorModule {
  
    @Override
    protected void configure() {
      addQuickMenu(MyQuickMenu.class);
    }
  }
  ```
  
  </details>

* Improve index generation

  <details>
  
  We can now specify order on column names with `ASC` or `DESC`.
  Some examples:
  ```xml
    <index columns="code,name"/>
    <index columns="code ASC,name DESC"/>
    <index columns="code,name DESC"/>
  ```
  
  We can also specify whether the index is unique. 
  ```xml
    <index columns="code,name" unique="true"/>
  ```
  This will replace and makes `<unique-constraint ... />` obsolete in future.
  
  </details>

* Allow to enable/disable cache from `axelor-config.properties`

  <details>
  
  `javax.persistence.sharedCache.mode` property in `axelor-config.properties` can be used
  to overwrite the `shared-cache-mode` from `persistence.xml`. It allow to enable/disable
  the shared cache mode (ie second-level cache).
  
  </details>

* Add support to manage multiple matching notify in `action-validate`

#### Fixed

* Fix undefined when calling search engine with route params
* Fix secure cookie on login attempt or change tenant request
* Prevent concurrent meta restoring
* Fix usage of multiple matching `info` in `action-validate`

  <details>
  
  In `action-validate`, if there is multiple `info` matching,
  only the first will be displayed on UI.
  
  </details>

* Fix radio style for grid selector
* Fix MySQL exception reporting
* Use whole value comparisons in datetime searches instead of using `between` operator

  <details>
  
  PostgreSQL JDBC driver 42.2.3+ introduce a change on how Timestamp are rounded : 
  https://github.com/pgjdbc/pgjdbc/issues/1211
  
  For example, `2018-06-03T23:59:59.999999999` is rounded to `2018-06-04 00:00:00`
  Previously `2018-06-03T23:59:59.999999999` was rounded to `2022-03-08 23:59:59.999`
  
  If nanoseconds is greater than 999999500 , value is now rounded. This is how PostgreSQL works :
  ```
  $ insert into some_table(date) VALUES('2018-06-03 23:59:59.999999999');
           date            
  ----------------------------
  2018-06-04 00:00:00
  ```
  
  The `between` operator used when searching on date/dateTime in UI have been updated to use 
  `>=` and `<` operators instead.
  
  </details>

* Fix JAXP usage

  <details>
  
  Thread safe usage of JAXP API. Use `XMLUtils` to protect XML Parsers from XXE attacks 
  but also disable external entity processing.
  
  </details>

#### Removed

* Remove deprecated usage of `hashKey` and `hashAll` attributes

  <details>
  
  Use `equalsInclude` and `equalsIncludeAll` instead.
  
  </details>

* Remove deprecated `Query#fetchSteam` methods

  <details>
  
  Use `Query#fetchStream` instead.
  
  </details>

* Remove domain model lang attribute

  <details>
  
  Domain models are generated in Java only.
  
  </details>

* Remove dependency to OpenCSV
* Remove deprecated `ActionHandler(ActionRequest)` method

  <details>
  
  Use `ActionExecutor#newActionHandler(ActionRequest)` instead
  
  </details>

* Remove deprecated `Context#getParentContext` method

  <details>
  
  Use `Context#getParent` instead.
  
  </details>

* Remove deprecated `cachable` domain attribute

  <details>
  
  Use `cacheable` attribute instead.
  
  </details>

* Remove ModuleChanged event

  <details>
  
  Since dropping support of removable modules, this event is no more used.
  
  </details>

* Remove deprecated `LoginRedirectException`

  <details>
  
  Use `WebUtils.issueRedirect` instead.
  
  </details>

* Remove IDE app launcher support
* Remove `setFlash` in `ActionResponse` to be aligned with `action-validate#info`

  <details>
  
  Use `setInfo` instead.
  
  </details>

* Remove legacy form widgets `<notebook>`, `<break>`, `<group>`, `<portlet>`, and `<include>`

  <details>
  
  Form widgets `<notebook>`, `<break>`, `<group>`, `<portlet>`, and `<include>` are deprecated.
  `cols` and `colWidths` form attributes used for legacy form layout are also deprecated.
  Those have be removed. Use panel layout instead.
  
  </details>

* Drop removable module support

  <details>
  
  The feature is not used by any axelor apps and has many
  technical issues.
  
  Run following SQL script to drop unnecessary columns
  
  ```
  alter table meta_module drop column installed;
  alter table meta_module drop column removable;
  alter table meta_module drop column pending;
  ```
  
  </details>


## 5.4.13 (2022-03-11)

#### Features

* Improve MasterDetail usage

  <details>
  
  MasterDetail form is no more displayed by default, but only when a line is selected and we want to create new record from.
  Also, when we add a new record, cancel current edit or click on back button, MaterDetail form is hidden.
  Add a new button "Add and new" to allow pushing current record in grid and quickly create a new one.
  
  </details>

* Add support to use MasterDetail with editable grid

  <details>
  
  When MasterDetail is used with editable grid, the form is readonly 
  and record is now live sync with the changes made in editable grid.
  
  </details>

#### Fixed

* Fix downloading attachment of action-report
* Fix permission checking with charts and custom views

  <details>
  
  Should not check for permission on `MetaView` when executing actions from charts.
  `onInit` action on charts use current model if calling from `action-view` else,
  `ScriptBindings` is used, so that we still have a context.
  
  </details>

* Fix file name downloaded in notify box
* Prevent grid sorting when there are dirty rows
* Allow dashboard drag&drop according to view customization permission

#### Security

* Add model attribute to action-report

  <details>
  
  If a model is not specified on action-report, permission is checked against context.
  
  </details>

* Check context read perm on action-report in case of attachment

  <details>
  
  If action-report attach the generated report to current object, it 
  now check for read permission on that object.
  
  </details>


## 5.4.12 (2022-03-04)

#### Fixed

* Add missing hilite style CSS
* Add missing attributes when processing widgetAttrs

## 5.4.11 (2022-02-23)

#### Features

* Allow access to custom fields in StringTemplate via dotted notation

  <details>
  
  StringTemplate doesn't allow `$` character in expressions. This is the way we use to access custom field
  from context : `$extraAttrs.myCustomField`. Accessing custom fields need to be prefixed by the related
  json field name (ex: `<SaleOrder.extraAttrs.myField>`or `<SaleOrder.attrs.anotherField>`). For custom fields
  in default `attrs` field, it is not needed to use prefix (ex: `<SaleOrder.myField>` ). However, it is
  recommended to always use the related json field name as prefix.
  
  </details>

* Add `axelor.view.watch.delay` and `axelor.view.watch.kinds` VM arguments

  <details>
  
  Defaults are as follows (working on Eclipse/IntelliJ on Linux):
  
  ```
  -Daxelor.view.watch.kinds=ENTRY_CREATE
  -Daxelor.view.watch.delay=300
  ```
  
  `axelor.view.watch.kinds` is a list of comma-separated strings of the following values:
  `ENTRY_CREATE`, `ENTRY_MODIFY`, `ENTRY_DELETE`.
  
  `axelor.view.watch.delay` is the update delay in milliseconds.
  
  Varying development environments may require special configuration in
  order to have view hotswap working.
  
  For example, on Windows, only `ENTRY_MODIFY` events might be triggered
  in some cases. Moreover, there may be a big gap between those events,
  requiring to increase the update delay. So one might want to use:
  
  ```
  -Daxelor.view.watch.kinds=ENTRY_CREATE,ENTRY_MODIFY
  -Daxelor.view.watch.delay=4000
  ```
  
  </details>

#### Fixed

* Fix parsing field widgetAttrs to preserve type depending on attribute

  <details>
  
  Extra widget attributes doesn't takes into account the type of each attributes. 
  This result of some unexpected behaviors. For example, the `x-true-text="true"` 
  cause error on `BooleanSelect` widget.
  Depending on the attribute, the value is converting to the right type.
  
  </details>

* Preserve selected flags from action values

  <details>
  
  Preserve selected flags when using `response.setValue(field, collection)`
  with collection of maps without version field.
  
  </details>

* Sort advanced search fields in lexicographic order
* Fix view hotswap when running embedded Tomcat
* Fix JS error when having favorite menus with duplicate names

  <details>
  
  In "Organize favorites", in the user changes a menu link, they may then add another menu with the same name,
  causing AngularJS error tracking for unique names.
  
  </details>

* Fix panel `showTitle` attribute
* Translate toolbar menu customization shown to technical staff
* Fix back button when view-parem `forceEdit` is used
* Fix m2m field update upon select record

  <details>
  
  Populated the m2m items all data. Only omit the version
  field (not fully populated as the record is already saved)
  
  </details>

* Fix position of field error message to be always to bottom of the field
* Fix file upload when using filename pattern

  <details>
  
  Property `file.upload.filename.pattern` should be evaluated on each upload
  in order to evaluate `{year}`/`{month}` or `{day}` keys.
  Moreover, when using a custom filename pattern, `{name}` should be placed at
  the end of the pattern. If omitted, he is appended by default at the end.
  
  </details>

#### Security

* Fix sanitize to prevent XSS vulnerabilities

## 5.4.10 (2022-02-02)

#### Fixed

* Fix AuthSecurityWarner initialization on app startup

  <details>
  
  Resource might be loaded before Guice is initialized, which causes "Guice is not initialized." error.
  
  </details>



## 5.4.9 (2022-01-31)

#### Deprecated

* Deprecate legacy form layout in favor of panel layout

  <details>
  
  Form widgets `<notebook>`, `<break>`, `<group>`, `<portlet>`, and `<include>` are deprecated.
  `cols` and `colWidths` form attributes used for legacy form layout are also deprecated.
  Those will be removed in the next major version. Use panel layout instead.
  
  </details>


* Deprecate Groovy domain models

  <details>
  
  In next major release, domain models will be generated in Java only.
  
  </details>



#### Features

* Add application setting to disable permission check on actions

  <details>
  
  Use `application.disable.action.permission = true` to disable permission checking on actions.
  That setting severely breaks security. It should be used with extreme caution and as a last resort only.
  It will be removed in the future.
  
  </details>


* Add application setting to disable permission check on relational fields

  <details>
  
  Use `application.disable.relational.field.permission = true` to disable permission checking on relational fields.
  That setting severely breaks security. It should be used with extreme caution and as a last resort only.
  It will be removed in the future.
  
  </details>


* Better visibility in DMS file details
* Add setting "auth.saml.logout.request.signed"

#### Fixed

* Allow to select multiple records in o2m/m2m inside an editor
* Prevent sorting when any row is dirty in o2m/m2m grid

  <details>
  
  This fixes losing changes after sorting on a column.
  
  </details>


* Fix default value of Boolean custom fields
* Fix checking for create permission on add actions
* Fix static/tooltip/button-group widget in views extensions
* Translate "Use offline..." text in dms file details
* Fix view hotswap with IntelliJ IDEA
* Check for permission in suggest box create actions

## 5.4.8 (2022-01-03)

#### Fixed

* Fix going into edit mode in editable grid with large horizontal scrolling
* Don’t trigger popup’s onLoad upon closing

  <details>
  
  When a popup was closing when saving the record, onLoad actions was triggered.
  This is unnecessary as the popup will be closed in all cases. It was also
  causing errors because the actions results was applied on a closed popup/form.
  
  </details>


* Fix m2m field update issue

  <details>
  
  The m2m items, upon select/edit should not be fully populated as the record
  is already saved (similar to m2o).
  In controllers, make sure to return a compact map in m2m fields, ie a
  list of map with the records ids. Then, the view will fetch the records
  with all necessary fields by itself.
  
  </details>



## 5.4.7 (2021-12-17)

#### Changes

* Only show access type error for all users

  <details>
  
  Technical and non-technical users are now notified the same undetailed access type error message.
  Details are shown in the logs only.
  
  </details>



#### Features

* Add support to UTF-8 BOM file in CSV Importer and `CSVFile` helpers class
* Log technical message for AuthSecurityException

#### Fixed

* Do not test pattern on empty string
* Check for create permission when adding row in editable grid
* Fix web service `perms` ignoring `action` parameter when no `id` is specified
* Translate tree node Expand/Collapse tooltip
* Exclude auditable fields, `created(On|By)` and `updated(On|By)`, when copying record

#### Security

* Check for relational permissions recursively in save request
* Escape data to prevent XSS vulnerabilities in JSP pages
* Upgrade to SLF4J 1.7.32 and logback 1.2.9

  <details>
  
  Mitigate LOGBACK-1591
  - slf4j to 1.7.32
  - logback to 1.2.9
  - jansi to 1.18
  
  See:
  - https://jira.qos.ch/browse/LOGBACK-1591
  - http://slf4j.org/log4shell.html
  - http://mailman.qos.ch/pipermail/announce/2021/000163.html
  
  </details>



## 5.4.6 (2021-11-30)

#### Changes

* Improve editable grid shortcuts to confirm/cancel edit

  <details>
  
  We can now use `ENTER` key to confirm current edit and `ESCAPE` key to cancel.
  
  </details>


#### Features

* Add `view.grid.editor.buttons` application configuration
* Add `canEdit` attribute to `panel-dashlet`

  <details>
  
  The new `canEdit` attribute on `panel-dashlet` will control whatever we can edit
  dashlet records even if the dashlet is in readonly. By default, when a `panel-dashlet`
  is in readonly (or main view is readonly mode or top container element is readonly),
  the record will be opened in popup. We can't edit the records. If editable, the record
  will be opened in a new tab, in readonly mode. We can then switch to edit mode to edit the record.
  
  Few more improvements :
  * Show file icon in grid dashlet when in readonly mode
  * Show pencil icon in grid dashlet when editable
  * Fix ignored dashlet attributes in dashboard
  
  </details>


#### Fixed

* Fix login failure event

  <details>
  
  Fire login failure event with form client, LDAP, and Basic Auth.
  
  Can't be triggered with other indirect clients like OAuth 2.0, OpenID
  Connect, SAML 2.0, etc.
  
  </details>

* Fix memory leak in session listener
* Fix horizontal scrolling in editable grid
* Fix field sometimes blank on initialization with CodeEditor widget
* Check for permission on messages requests
* Fix `commons.codec` dependency conflict when using SAML
* Check for read permission on action-record changing dummy fields
* Translate grid selection checkbox tooltip
* Fix readonly dashlet with forceEdit should open readonly popup in readonly mode
* Fix Gantt view sometimes blank on initialization
* Fix resetting of shared custom views
* Fix keyboard shortcuts on Mac-like devices

  <details>
  
  When Macintosh is detected, use specific keyboard shortcuts.
  Macintosh keyboards don’t have AltGr nor Insert keys.
  
  </details>

* Fix concurrent update error when moving tasks in Gantt view
* Check for permission on followers requests
* Fix NPE with null password to `AuthService#passwordMatchesPattern()`
* Fix blocked loading of custom translations
* Fix ignored regex on custom field
* Check for permission on attachments requests
* Fix dashlet with popup should open editable popup in editable mode

## 5.4.5 (2021-10-21)

#### Changes

* Add view customization permission on Group and `view.customization` application configuration

  <details>

  All users used to be allowed to customize views, and only admins could share.
  Now, view customization permission is checked on Group, and can be set to "Not allowed" (default),
  "Can customize", or "Can share".
  `view.customization` application configuration defaults to `true`.
  If set to `false`, Customize menu and custom views are disabled.

  </details>


#### Fixed

* Fix null value with BooleanRadio when switching from a record to another
* Fix permission check on action targeting a different model from current record

## 5.4.4 (2021-10-01)

#### Fixed

* Check for permission on action model if defined
* Fix infinite recursive popup grid loading
* Fix alignment of meta JSON field using contextField
* Fix JS error when M2O field has no namecolumn
* Add scrollbar in DMS Permission dialog box
* Fix missing ID on created records from TagSelect widget
* Fix detaching of one-to-one from non-owning side

#### Security

* Prevent script execution from custom stylesheet

## 5.4.3 (2021-09-08)

#### Changes

* Serialize Time with seconds

  <details>
  
  Time is now serialized with formatter "HH:mm:ss".
  Seconds are still not displayed by default in client (need to use `x-seconds="true"`).
  
  </details>

* Require manual closing of datetime picker
* Disable translation joins on queries by default

  <details>
  
  Add Query.translate() and Filter.translate() to enable translation joins.

  REST API now accepts parameter `translate` to enable translation joins
  on criteria and for ordering, but never for domains.

  Web client always uses the `translate` parameter.
  
  </details>


#### Features

* Clarify title/placeholder of user password fields

  <details>
  
  Clarify title/placeholder of user password fields in form view and change password page.

  * Change title depending on existing or new user
  * Add help popovers
  * Add password pattern help using `user.password.pattern.title` (customized via translation)
  
  </details>

* Use select control type on year, month, and time in datetime widget
* Add support for related parameters in domain expressions

  <details>
  
  Character `$` is used to access related fields in domain expression parameters.
  This can be used to access other JSON fields. Example:

  ```
  "self.product = :$extraAttrs$myCustomProduct"
  ```
  
  </details>

* Add view-param `grid-width` to set width of grid in details-view

  <details>
  
  Example:

  ```xml
  <action-view name="sale.orders" model="com.axelor.sale.db.Order" title="Sale Orders">
    <view type="grid" name="order-grid" />
    <view type="form" name="order-form" />
    <view-param name="details-view" value="true" />
    <view-param name="grid-width" value="25%" />
  </action-view>
  ```
  
  </details>

* Add multi-tenant aware thread implementation

  <details>
  
  The `com.axelor.db.tenants.TenantAware` is a custom `Thread` implementation
  that keeps track of current tenant and runs the thread in transaction with
  current tenant in the context.
  
  </details>

* Add setter methods for all attributes in XMLInput/XMLBind CSVInput/CSVBind
* Support adaptive date/time/datetime quick search in grid view
* Add support for CSV/XML data import of custom fields

  <details>
  
  Example:

  ```xml
  <!-- for CSV data -->
  <input file="some-data.csv" ...>
    <bind column="some" to="$attrs.some" />
  </input>

  <!-- for XML data -->
  <input file="some-data.xml" ...>
    <bind node="some" to="$attrs.some" />
  </input>
  ```

  For custom model records, you can specify `json-model` in order to automatically bind jsonModel,
  handle search domain, and set namecolumn.

  CSV import example:

  ```xml
  <input file="data.csv" json-model="ElectricityBillSubscription"
      search="json_extract_text(self.attrs, 'name') = :name">
    <bind column="name" to="$attrs.name" />
    <bind column="startDate" to="$attrs.startDate" adapter="LocalDate" />
    <bind column="endDate" to="$attrs.endDate" adapter="LocalDate" />

    <bind to="$attrs.billSubscription"
        search="json_extract_text(self.attrs, 'name') = :billName">
      <bind column="billName" to="$attrs.name" />
    </bind>
  </input>
  ```

  ```csv
  name,startDate,endDate,billName
  "Bill Jan2021","01/01/2021","31/01/2021","Test01"
  "Bill Feb2021","01/02/2021","28/02/2021","Test02"
  ```

  XML import example:

  ```xml
  <bind node="electricityBillSubscription" json-model="ElectricityBillSubscription" update="true"
      search="json_extract_text(self.attrs, 'name') = :_electricityBillSubscriptionName">
    <bind node="name" to="_electricityBillSubscriptionName" />
    <bind node="name" to="$attrs.name" />
    <bind node="startDate" to="$attrs.startDate" adapter="LocalDate" />
    <bind node="endDate" to="$attrs.endDate" adapter="LocalDate" />

    <bind node="billSubscription" to="$attrs.billSubscription" update="true"
        search="json_extract_text(self.attrs, 'name') = :_billSubscriptionName">
      <bind node="@name" to="_billSubscriptionName" />
      <bind node="@name" to="$attrs.name" />
    </bind>
  </bind>
  ```

  ```xml
  <electricityBillSubscription>
    <name>Bill Jan2021</name>
    <startDate>01/01/2021</startDate>
    <endDate>31/01/2021</endDate>
    <billSubscription name="Test01"/>
  </electricityBillSubscription>
  ```
  
  </details>

* Support `x-seconds` attribute on DateTime and Time widgets
* Add ui-action-context attribute to use with ui-action-click

  <details>
  
  The `ui-action-context="scopeVariable"` can be used with `ui-action-click` in custom view
  template to provide extra details in action context.
  
  </details>

* Expose $scope.openTab as axelor.$openTab

  <details>
  
  The api can be useful for some apps loading in iframe.
  
  </details>

* Improve the indicator and delay the wait cursor

  <details>
  
  - delay showing of loading indicator by 1 second
  - delay wait cursor till the wait spinner appears
  
  </details>

* Add support for view auto-reloading

  <details>
  
  Use `view-param` named `auto-reload`, with value specified in seconds. Example:

  ```xml
    <view-param name="auto-reload" value="30" />
  ```

  Auto-reloading is paused when tab is not selected or when record is in edit mode.
  
  </details>

* Add JSON field meta info on all views

#### Fixed

* Fix CSS of invalid BinaryLink
* Fix value update action after save in one-to-many/many-to-many editor
* Fix dirty form when there are custom multirelational fields
* Fix grid search on custom fields
* Fix grid customization when view has duplicate fields
* Fix duplicate computed views after upgrade
* Fix `ActionResponse.setReload` and `refresh-tab` signal from editor
* Fix NavSelect widget with M2O record having an empty name
* Fix attribute `x-currency` not taken into account
* Fix user notifications for followers added by email address
* Fix dashlet search triggering buttons
* Log errors when persisting JpaFixture items
* Exclude UI custom fields from exports
* Fix missing handling of LocalTime in JpaFixture::load

  <details>
  
  When loading fixture in tests with LocalTime fields, the JpaFixture throw
  a exception because LocalTime hasn't appropriate constructor.

  ```
  Caused by: org.yaml.snakeyaml.error.YAMLException: No single argument constructor found for class java.time.LocalTime : null
  ```
  
  </details>

* Wait for actions before confirming line on editable grid
* Fix unsupported regular expression in WebKit causing empty grid
* Fix currency filter with symbols other than $, €, ¥, £
* Use number input type on number fields
* Fix text value escape on M2O names
* Fix multi-tenant datasource configuration
* Fix generated code when setting `default="null"` on nullable boolean
* Fix broken free search
* Fix stuck login dialog after wrong attempt
* Fix recursive child grid view in popup
* Fix readonly input in mass update form when grid has readonlyIf in fields
* Fix grid export IndexOutOfBoundsException with collection columns and translatable order by
* Fix ignored grid orderBy when applying default search filters
* Fix empty PDF tab with latest Chrome
* Fix readonly button on grid
* Fix mass applying same permission to children DMS files
* Fix free search with custom fields
* Fix button showIf/hideIf on editable grid
* Fix unnecessary fetch from field editor
* Fix missing "contains" search with MultiSelect widget
* Fix dirty checking issue with relational fields

  <details>
  
  When some o2m has an onChange action that updates parent record with
  `setValues(instance)` the o2m items were wrongly marked as dirty if
  the o2m doesn't have named column.
  
  </details>

* Fix multi-tenant datasource configuration
* Preserve view-param showArchived when clearing filters
* Fix uploading of meta files in form view
* Fix Binary/BinaryLink in editable grid

#### Security

* Check for permissions when calling actions
* Check for permissions on dotted fields

  <details>
  
  Check for permissions on dotted fields:

  * Filter permitted dotted fields in fetch request
  * Filter permitted dotted fields in search request
  * Filter permitted fields in ActionRequest.setValue
  * Check for create/write permissions on related fields in save request
  * Check for write permissions on editable grid
  * Hide dotted fields if user has no read permissions

  Users are still permitted to read dotted fields’ id, version, and namecolumn
  regardless of permissions.
  
  </details>


## 5.4.2 (2021-04-15)

#### Changes

* Default setting `data.export.encoding` to UTF-8

  <details>
  
  When UTF-8 is used, BOM is added so that Excel recognizes the encoding.
  
  </details>

* Remove jcenter and prevent dependency confusion risk
* Migrate from opencsv to commons-csv
* Format exported dates and times with client locale

  <details>
  
  Setting `data.export.locale` to set a fixed locale for all exports.
  Setting `data.export.separator` defaults to ';'.
  Only numbers (integer, long, decimal) were formatted using client locale.
  Dates, times, and datetimes now also use client locale.
  Setting `date.format` is removed.
  
  </details>


#### Features

* Improve grid customization

  <details>
  
  <ul>
    <li>Show translated field titles besides field names</li>
    <li>Allow to save column widths</li>
    <li>Add removed fields as hidden as to preserve expressions</li>
    <li>Add reset customization button</li>
    <li>Don’t delete customized grids when clearing cache</li>
    <li>List fields from search filters in selector</li>
    <li>Remove move icons in selector</li>
  </ul>
  
  </details>

* Add action to select a `panel` in `panel-tabs`

  <details>
  
  Example:

  ```xml
    <form ...>
     ...
     <panel-tabs>
      <panel title="One" name="t1"></panel>
      <panel title="Two" name="t2"></panel>
     </panel-tabs>
    </form>

    <action-attrs ...>
      <attribute name="active" for="t1" expr="true" />
    </action-attrs>
  ```
  
  </details>

* Apply translations on translatable fields and enums for data exports

  <details>
  
  Only headers and selections were translated.
  Translatable fields and enums are now also translated.
  
  </details>

* Add "auth.ldap.user.dn.format" and "auth.ldap.user.username.attribute" configurations
* Add `view-param` named `default-search-filters` to apply filters by default

  <details>
  
  Example:

  ```xml
    <view-param name="search-filters" value="filter-sales"/>
    <view-param name="default-search-filters" value="confirmed,highValue"/>
  ```

  Filters named `confirmed` and `highValue` will be applied by default.
  For that usage, use new attribute `name` for each `filter` in `search-filters`.
  
  </details>

* Use thin scrollbars on grids inside form views
* Add workflow status help

  <details>
  
  Example:

  ```java
  public void onFetch(
      @Observes @Named(RequestEvent.FETCH) @EntityType(Order.class) PostRequest event) {
    @SuppressWarnings("unchecked")
    final Map<String, Object> values = (Map<String, Object>) event.getResponse().getItem(0);
    if (values != null) {
      List<Map<String, Object>> status = new ArrayList<>();
      status.add(
          ImmutableMap.of(
              "name", "s1", "title", I18n.get("Status 1"), "color", "red", "help", "Some help…"));
      values.put("$wkfStatus", status);
    }
  }
  ```
  
  </details>


#### Fixed

* Fix NPE when loading extension views without corresponding base views
* Fix "cn" used as ID attribute with LDAP
* Fix Unexpected character '`' when running npm-build
* Fix evaluation of filter `if` and `if-module` attributes
* Fix grid column widths when popup is opened as maximized
* Fix attrs reset for bpmn use case
* Fix looped view loading when grid has o2m/m2m fields referencing themselves
* Fix duplicate onChange call with BooleanRadio widget
* Merge menus into mobile toolbar
* Fix fetching LDAP attributes with Active Directory
* Fix row height when using Image widget in editable grid
* Fix missing ID in context after 'save' action in multirelational editor
* Apply view attributes to new records as well
* Fix "Cannot change session ID" exception when using basic auth on non-secure requests
* Fix search query causing dirty form
* Fix Phone widget readonly width adjustment
* Fix wrong query string append with dynamic URL in HTML view

## 5.4.1 (2021-02-10)

#### Changes

* Add `x-show-bars` attribute to `panel-related` and `panel-dashlet` (disabled by default)

#### Features

* Add relative dates and current user/group criteria to advanced search
* Add support for defining Column attributes `insertable` and `updatable`
* Add tel link on grid view when using Phone widget
* Add support for aggregation with Duration widget
* Add flag dropdown to Phone widget

#### Fixed

* Fix requiredIf with some widgets (BinaryLink, CodeEditor…)
* Fix setting null on nullable number field with min/max attributes
* Preserve per module web resource inclusion order
* Fix sidebar="false" panel attribute evaluation
* Fix URL widget in grid view
* Fix grid field hilite overwritten by grid view hilite
* Fix parent toolbar/menubar displayed in dashlet
* Fix search engine action menu selection
* Group M2O/O2O by ID in grid, but show namecolumn
* Set "SameSite=None" attribute on cookies to allow CORS requests
* Set first day of week according to browser locale
* Fix modern theme button alignment in O2M editor

#### Security

* Fix HTML sanitization

## 5.4.0 (2020-12-18)

#### Changes

* `equalsInclude` attribute marks field to be included in equality test, instead of `hashKey`
* Improve keyboard shortcuts

  <details>
  
  The keyboard shortcuts Ctrl+F and Ctrl+G were conflicting with browser shortcuts,
  they are now changed to Alt+F and Alt+G respectively. Also fixed some broken shortcuts.

  Also added a dialog to show keyboard shortcuts which can be shown from the top-right corner menu.
  
  </details>

* Add support for toolbar and menubar on dashlet and o2m/m2m widgets

  <details>
  
  For the toolbar, only first 3 buttons will be visible.
  For the menubar, only first menu will be visible.
  
  </details>


#### Deprecated

* Deprecate `LoginRedirectException` in favor of `WebUtils.issueRedirect`
* Deprecate `hashKey` and `hashAll` in favor of `equalsInclude` and `equalsIncludeAll`

#### Features

* Add support for separators in editors
* Add menu item in form view to show workflow
* Add support for x-show-icons on panel-related
* Add `refresh-tab` response signal
* Add helper api to easily open html tabs
* Add support for defining view attrs externally
* Improve Context api to allow overriding proxy usage
* Add support searching on translatable fields
* Override/add filters: currency, percent, number, date
* Implement grid view customization

  <details>
  
  We can now customize the top-level grid view with a customize column menu and
  selecting/removing/reordering fields.

  The customized view can be shared to all users by admins.
  
  </details>

* Add support for more hilite colors

  <details>
  
  We can now use more named colors with hilite besides the hilite styles.
  
  </details>

* Alert when DMS files and exported files are not found
* Implement `json_set` function

  <details>
  
  The function can be used to update json values using JPQL.

  For example:

  ```sql
  UPDATE
    Product p
  SET
    p.attrs = json_set(p.attrs, 'seller.name', '"Some NAME"')
  WHERE
    json_extract(p.attrs, 'seller', 'id') = 1
  ```
  
  </details>

* Add before transaction complete event

  <details>
  
  The event is for internal purpose only. We may promote it as public api in some future version.
  The event is fired with total number of records updated/deleted in current transaction.
  
  </details>

* Numeric and Decimal format support based on browser language
* Date and DateTime format support based on browser language
* Add support for `help` attribute to panel widgets
* Add support for field tooltip templates
* Support CSS on grid buttons
* Add `mail.smtp.from` application setting
* Add support to show workflow status on top of the form
* Charts decimal/date format support based on browser language
* Add current view type and view names in action context
* Add reset search terms icon to advance search box
* Add support for maximized popups for relational fields

  <details>
  
  We can specify `x-popup-maximized="all|editor|selector" to specify whether to show
  the `editor`, `selector` or both popups in maximized state.
  
  </details>


#### Fixed

* Fix random mail sender test failure
* Fix save action in editable grid
* Fix onChange issue on html widget
* Fix select image button text in HTML widget
* Fix `LoginRedirectException`
* Fix js error when clicking on html view tab
* Fix Response#setValue with $-prefixed dummy field
* Fix PDF visibility in DMS when changing tab content with Chrome
* Fix image-select widget issues
* Fix updating of selected flags for JavaScript expressions
* Fix dotted target-name on TagSelect and many-to-one fields
* Prevent sorting/searching on transient/dummy field in grid
* Reset attributes when navigating and refreshing records from form view
* Fix image height in *-to-many editor
* Prevent searching on relational field without namecolumn in grid
* Fix OK/Cancel button order on editable grid and master-detail
* Fix needless recomputing of views
* Fix visibility evaluation of field using master-detail widget
* Fix empty selection in grid when action initialized selection state
* Fix $fmt with dotted fields and custom views
* Remove unsupported grid button attributes from autocompletion
* Add missing Indian momentjs locales
* Fix o2m/m2m field button bar display
* Fix `target-name` attribute issue on m2o field of grid view
* Fix save action in master-detail
* Apply view extensions by matching name and groups
* Fix NPE when finding computed field dependencies
* Fix translation of dotted target names
* Fix touchpad click to confirm on a popup
* Fix custom field value expression doesn't make form dirty
* Fix Thai momentjs locale
* Fix rendering of buttons and action execution on editable grids
* Fix TypeError when several editable grids have the same dummy fields
* Add missing English momentjs locales
* Fix reference names in json fields

  <details>
  
  When name field of the reference record is changed, the name value saved in json field
  is updated to reflect the updated name.
  
  </details>

* Fix custom fields support in string templates
* Fix editable row reset to old value issue
* Fix context issue with chart click actions

## 5.3.7 (2020-12-18)

#### Features

* Add index to MetaHelp on model, language, view fields

#### Fixed

* Prevent usage of proxies in search request
* Fix o2m/m2m sorting issue caused by permissions
* Fix missing CSRF header upon successful login request
* Fix searching for o2o field on the non-owning side
* Fix MenuItem tag display with null/empty value
* Save decimal value with applied x-scale from view
* Fix translation of dotted fields on references in grid view
* Fix one-to-many editor validation issue
* Fix misalignment on grid group row
* Fix required field validation on contextual custom fields

## 5.3.6 (2020-10-14)

#### Features

* Go into edit on focus for multiline string in editable grid
* Add index to MailMessage on relatedModel, relatedId fields

#### Fixed

* Fix change tracking translation issue
* Hide edit in grid icons bar while editing row
* Set authentication request character encoding to UTF-8
* Fix customize dashboard with drag & drop
* Fix Context#asType invalid type error message
* Fix boolean field focus issue in editable grid
* Fix timezone issue with start/end time in calendar view popup
* Fix timezone issue with start/end of calendar view
* Fix single-select widget on integer field
* Fix readonly rendering of inline HTML widget
* Fix readonly rendering of large string in editable grid
* Fix setting of o2m/m2m field in editable grid from action
* Fix PostAction event issue for action-record
* Fix buttons not shown when using x-selector="checkbox"
* Ignore hiding of fields in editable grid
* Update dotted fields in grid from form dotted fields
* Fix onChange not triggered when setting date to null
* Fix rare emptied row on save issue in editable grid
* Fix multi-month calendar events missing for intermediate months

## 5.3.5 (2020-08-17)

#### Features

* Add support for help on panel-dashlet
* Add support for per grid widget checkbox selector

  <details>
  
  Now we can use `x-selector="checkbox"` on grid view or panel-related to
  show checkbox selection.
  
  </details>

* Fix gantt view pagination issue

#### Fixed

* Fix switching to another tenant
* Fix dotted fields disappearing after edition on top-level editable grids
* Fix black and white tag colors
* Fix empty form after saving new records in details view
* Fix downloading of meta files if parent has collections of meta files
* Fix missing read permission on user/group for DMS
* Fetch target names for tag-select widget
* Fix view extension replace on menubar/toolbar/panel-mail
* Fix action method call with arguments having ':'
* Fix charts with zero in series
* Fix emptied field not present in grid after opening popup form
* Fix truncated title and vertical alignment of tag-select with modern theme
* Fix view watching from mixed location kinds
* Fix nullable boolean radio displayed as false
* Fix search null or empty string field values
* Fix grid toolbar button js expression issue
* Fix onNew on editable grid row
* Fix column menu show/hide item in grid view
* Fix wrong positional parameter resolution from query binder
* Fix mail base64 image max line (RFC2045)
* Fix dotted field translation in grid view
* Fix NavSelect and MenuBar size adjust issue

#### Security

* Validate external input for tree view

## 5.3.4 (2020-06-29)

#### Fixed

* Fix mass update on grid view because of dotted fields
* Restrict system page to technical staff
* Fix the button _signal in context
* Fix query building with empty logical filters
* Formatting the restoring meta execution time in ISO time
* Fix NPE caused by response exception handler
* Fix missing delimiter in advanced search when no export permission
* Fix input left padding with ImageSelect widget when element is not shown immediately after edit mode
* Consider permissions and showArchived view-param for tag-count
* Fix meta file link with no parent

## 5.3.3 (2020-06-05)

#### Features

* Add support for font config for birt reports
* Support axis titles on charts
* Support cards, kanban, and calendar views on dashlets
* Add support to use m2o fields for kanban columns
* Allow to show additional details with ref-text widget
* Add single-select and improve multi-select widget with color support
* Add support for popup editor on cards and kanban views

  <details>
  
  A new attribute `edit-window` is added with the following values:

  * `self` - show editor in same tab
  * `popup` - show popup editor
  * `popup-new` - show popup editor for new records only
  
  </details>

#### Fixed

* Fix unnecessary requests for name values
* Fix validation error in editable grid
* Fix "Invalid or non-matching id" when committing edit in grid
* Fix meta loading deadlock when creating groups
* Fix group creation when generating computed views
* Fix custom view refresh issue in dashlet
* Fix embedded tomcat runner
* Preserve column order in data exported from charts
* Fix text value escape in grid widget
* Fix details-view with grouped grid not working
* Fix path resolution in view watcher under Windows
* Fix CSRF token cookie when using SSO
* Allow deletion of one-to-one on the non-owning side and change of owner
* Allow inline edit with grouped grid
* Fix no file in response when observing exports
* Fix view watcher on application's resources
* Don't show fallback characters instead of icons on slow network
* Fix editable grid issue in details-view
* Fix setting of owner of one-to-one
* Fix checkbox alignment in editable grid

## 5.3.2 (2020-04-16)

#### Features

* Limit parallel meta loading to maximumPoolSize
* Add support for providing webapp resources from modules
* Add support for registering static web resources

#### Fixed

* Fix backward-compatible authentication via login.jsp
* Fix compile classpath issue
* Fix context issue with js expressions on m2o fields
* Fix spurious onLoad execution from calendar and kanban views
* Fix new line confirmation in editable grid after ActionResponse#setValues
* Align kanban hilite colors with grid hilite colors
* Fix view watching with IntelliJ IDEA
* Wait for actions when committing changes in editable grid
* Fix embedded tomcat run task issue
* Fix large text field in editable grid widget
* Use permission filter in order to count attachments
* Context from grid row editor should use row record

## 5.3.1 (2020-04-15)

#### Breaking Changes

* In order to migrate User activateOn and expiresOn fields, use these SQL statements:
  
    ```sql
    ALTER TABLE auth_user ALTER COLUMN activate_on TYPE timestamp;
    ALTER TABLE auth_user ALTER COLUMN expires_on TYPE timestamp;
    UPDATE meta_field SET type_name = 'LocalDateTime' FROM meta_model WHERE meta_field.meta_model = meta_model.id AND meta_model.name = 'User' AND meta_field.name IN ('activateOn', 'expiresOn');
    ```

#### Changes

* Change User activateOn and expiresOn to datetime type

#### Features

* Check for active user on every pre-request

#### Fixed

* Fix CSS of calendar bubble content
* Fix menu title wrapping with tag
* Fix combining selection simple filters with custom filters
* Don't show kanban popover with empty content
* Fix NavSelect widget on integer selection
* Fix extension insert before in declaration order
* Fix Overview panel randomly still present despite having custom panel as first element
* Do not allow deleting tasks when scheduler is running
* Fix tomcat 8.5.51 issue caused by javax.el service discovery
* Fix unknown tracked field detection when using inheritance
* Fix target-name on dotted field after selection from grid
* Fix same duration widget mask being applied to subsequent fields

## 5.3.0 (2020-01-24)

#### Changes

* Remove licenseCheck from check dependencies
* Upgrade to Gradle 5.6.4
* Run license task on src files only
* Migrate LDAP to pac4j
* Refactor grid widget to improve inline edit experience
* Make button onClick attribute required
* Upgrade to Spotless 3.24.3
* Improve hotswap-agent support
* Upgrade to pac4j 3.8.3
* Upgrade to Guava 28.1

#### Features

* Increase custom field conditions limit to 512 characters
* Add canNew, canEdit, and canDelete to cards and kanban views
* Set default logger config for pac4j package
* Centralize properties from application.properties
* Display `conditionToCheck` and `moduleToCheck` on meta menu view
* Add Request#getUser() method that returns current session user
* Log request data at trace level
* Watch for view updates
* Improve display of forced password change
* Sort mail messages by most recent reply if any
* Only update visible tags
* Parallelize loading of models, views, and i18n with rollback per module
* Add tel link and pattern to phone widget
* Add direct basic authentication client
* Add position "inside" for view extensions
* Add support for groupBy on custom models
* Add hideIf and showIf support to grid buttons
* Add responseBindingType to SAML configuration
* Ability to toggle chart legends and ellipsed labels with tooltip
* Add hideLegend config to charts
* Add support for NavSelect widget on many-to-one fields
* Scan for css/js files to minify
* Trigger onChange on Enter key in simple fields

#### Fixed

* Fix 'x-' prefixed extra attributes on custom fields
* Fix Query#update with null value
* Re-throw exception on Birt report generation exception
* Fix JNDI data source not working
* Fix currency formatting with IE11
* Do not set WWW-Authenticate response header when request has no basic auth header
* Fix scope syncing on charts
* Fix inline-checkbox widget title wrapping issue
* Use styled checkbox for grid row selector
* Block the UI as early as possible on action call
* Fix Query#update when query has ORDER BY clause
* Fix Gantt view scrolling
* Fix untranslated namecolumn when fetching missing values
* Fix m2o field in grid not showing translated value
* Fix dashlet refresh issue
* Update menu tags using `tag-count`
* Fix tag-select widget's search field width issue
* Fix popup editor issue when a tab is opened from it
* Fix widget name check inconsistencies
* Allow empty panel inside a panel
* Fix migration of existing views to extension views
* Fix Query#update with several fields
* Fix archived records not displayed when simple filter and advanced filter are applied
* Fix redirection to originally requested URL
* Fix dashlet refresh issue
* Remove deprecated api usage from Logger injection support
* Fix wrong redirection to favicon.ico
* Fix table layout on field editors
* Fix month variable replacement in app settings value
* Fix multi-tenancy with pac4j
* Fix tests
* Fix error popup with empty message not showing up in prod mode
* Remove showTitle from panel-related
* Fix query filtering on collections and using order by
* Fix duplicate m2m item issue
* Fix dotted fields setting unwanted intermediate records
* Silent requests should not hide loading indicator
* Fix ajax login should re-execute pending requests
* Fix target-name of a custom field in grid view when visibleInGrid is not used
* Fix issue caused by empty string value on decimal field
* Fix untranslated namecolumn value in TagSelect
* Fix collection fields with editor validation issue
* Fix relative style/script source in JSP
* Fix popup editor issue when a tab is opened from it
* Fix selecting a m2o on editable grid when server is slow
* Fix SAML postLogoutURL for webapps deployed at root
* Fix DMS file being automatically downloaded on form view

#### Security

* Fix XSS vulnerability with html widget
* Add CSRF protection using pac4j CSRF authorizer

## 5.2.1 (2019-09-19)

#### Features

* Add `generateChangelog` gradle task to generate final CHANGELOG from unreleased entries.
* Log tracking of unknown fields during code generation

#### Fixed

* Fix distinct query issue when search is done on o2m/m2m
* Prohibited usage of unsupported xml attributes in grid fields.
* Invalid meta and translations when restore is done

#### Security

* Fix security issue in criteria filter, Query and json function

## 5.2.0 (2019-09-16)

#### Enhancements

* Upgrade to Shiro 1.4.1
* Add support for SAML2
* Add support for OpenID Connect
* Log authentication failure
* Add support for sidebar panels with custom models
* Add support for customer field type spacer
* Add "auth." prefix to authentication-related configurations
* Implement CAS via pac4j
* Use view.menubar.location instead of application.menu
* Add support for more CAS client types
* Improved custom field views
* Do not generate default value if default="" is given
* Add support for OAuth
* Allow to set target-name attribute on custom model fields
* Use woodstox StAX API for data import
* Improve tracking message formatting
* Add support for toolbar and menubar in kanban view
* Prohibited usage of some unsupported editor attributes
* Add domain field name validation for few more reserved names
* Add x-big and x-seconds attributes
* Configure XStream security framework
* Add scale attribute to chart series
* Clear the persistence context after job is executed
* Add /ws/public/\*\* as anonymous rest endpoints
* Use icon if user profile image is not set
* Have boolean-radio behave like radio-select
* Restrict x-direction values to "horizontal" and "vertical"
* Improve Meta Scheduler views and usage
* Add x-accept support to specify file type filters

#### Bugs

* Skip linked bindings when finding observers
* Fix json field ordering issue
* Fix unable to open form from grid dashlet
* Fix calendar view color issue
* Fix BinaryLink & Image widgets with custom json models
* Fix advanced search on transient fields
* Fix showIf expression on custom o2m field issue
* Fix button in custom model grid
* Fix readonlyIf on button of custom model grid
* Fix downloading of meta files in JSON fields
* Fix EntityHelper#hashCode inconsistent with generated entities
* Fix toolbar buttons remains highlighted on view switch
* Center mail message avast image
* Fix grid selected rows (exclude group rows)
* Fix display time on calendar
* Fix hilite expression parsing issue
* Fix pagination issue caused by use of query cache
* Fix pagination issue when searching on collection fields
* Fix file name encoding when upload DMSFile
* Fix random view if view by name not found
* Fix initParam with field override
* Fix img-button css
* Fix dotted fields issue
* Fix image widget reload issue
* Do not show concurrent updates error on missing reference
* Fix html widget empty value issue
* Exclude archived records from tag-count
* Fix empty PDF tab with Chrome
* Extract title attribute in extensions for i18n
* Fix kanban view tooltip placement issue
* Fix redirect issue with https proxy
* Fix nested editor issue with canSelect=false
* Fix ImageSelect widget regression
* Fix translation value of translatable m2o name field is not reflected
* Fix old-style view extensions when base view has panel-mail
* Fix advanced search state sharing on card and grid views
* Fix if condition on help element of grid view not working
* Fix change tracking emails having null values
* Fix csv import on collection fields
* Fix xml import on collection fields
* Allow non csv column in local context values
* Fix xml import on collection fields

#### Breaking Changes

* View lookup: if a view with a specified name is not found,
  no view is now returned, instead of returning another unpredictable view.
* All authentication-related configurations are now prefixed with "auth.".
  For instance, previous "cas.\*" configurations are now named "auth.cas.\*".
* `x-direction` attribute (used with `boolean-radio` and `radio-select` widgets)
  is now restricted to either "horizontal" or "vertical".

## 5.1.0 (2019-06-28)

Check the `5.1.0-rc1` and `5.1.0-rc2` Changelog for complete list of changes.

#### Enhancements

* Only log when non-existing field is referenced in expressions (breaking change)
* Properly handle expression errors
* Prevent calling arbitrary methods with action (breaking change)
* Add app startup/shutdown events

#### Bugs

* Fix value assignment in EL expression
* Fix duplicate results in number of attachments

#### Breaking Changes

* Calling arbitrary methods from action-method or with `call:` is not allowed.

  All such methods should be annotated with `@CallMethod` annotation (`com.axelor.meta.CallMethod`).
  Use following shell command to find all the method calls in your code base:

  ```
  $ grep -P "(expr)(\s*=\s*)(\"call:([^\"]+\([^\"]+)\")" -r * -oh --include="*.xml" \
    | cut -d\" -f2 \
    | sed -E 's|call:\s*||g' | cut -d\( -f1 | sort -u
  ```

* Scripting expressions in xml actions are now not silent on errors.

  All errors during expression evaluation except missing attribute error are propagated to the user
  so evaluation of such expressions will fail and ultimately actions too.

* DMS permissions (requires manual intervention)

  DMS permissions are created and removed recursively for all children documents/folders.
  The `perm.dms.file.__parent__` permission is no longer used. Also, DMS permissions are readonly
  once created, but can be removed.

  Remove DMS file related permissions (permissions with a name starting with `perm.dms.file`).
  Those permissions will be recreated and updated after adding new DMS permissions.
  Remove and add back DMS permissions on your documents from the DMS view.

* The `context.appLogo` method should now use helper `MetaFiles#getDownloadLink` instead of
  returning `MetaFile`.

* On ManyToOne fields, `canEdit` attribute is `false` by default now.

* The `freeSearch="name"` to search on name field should be changed to `freeSearch="actualNameField"`

* The `cachable` attribute on `entity` definition is now deprecated and replaced with `cacheable`.

  ```
  $ find -iregex ".*domains.*.xml" | xargs -l sed -i 's|cachable="|cacheable="|g'
  ```

## 5.0.16 (2019-06-28)

#### Enhancements

* Add support for base64 encoded images with mail builder api
* Set Monday as first day of week in calendar view for French locale
* Improve date formatting in calendar view for French locale

#### Bugs

* Fix calendar view layout issues
* Fix dms permissions preventing attachments
* Fix grid row selection issue when deleting o2m/m2m items
* Fix wrong context with grid button after 'save' action
* Fix fetch request data serialization issue caused by rollbacked transaction
* Fix js expressions with dummy not evaluated inside field editors
* Fix named width styles not working
* Fix double escaping of html chars in grid widget
* Fix grid rendering issue caused by page change from form view
* Do not fetch archived records in tree view
* Fix x-can-copy issue (unable to copy if parent is now saved)
* Fix NestedEditor issue when name field is missing

## 5.0.15 (2019-05-31)

#### Enhancements

* Upgrade to hotswap-agent 1.3.0
* Improve help popover

#### Bugs

* Fix deprecated nested editor issue (for legacy use cases)
* Fix tracking message formatting issue
* Fix lost changes issue with child grid
* Fix change tracking clean up issue (transaction rollback should discard tracking)
* Fix o2m list editor layout (IE11 issue)
* Fix placeholder color (IE11 issue)
* Fix no scrollbar in popup editor (IE11 issue)
* Patch jquery for possible XSS vulnerability (jquery/jquery#2432)
* Patch jquery for CVE-2019-11358
* Fix boolean-radio widget on chrome
* Fix file handle not closed issues

## 5.1.0-rc2 (03-06-2019)

#### Bugs

* Fix lost changes issue with child grid
* Fix use of "member of" in domain expression
* Fix duplicate DMS file results when several DMS permissions are matched
* Fix hiding of error message div with IE

#### Enhancements

* Upgrade to hotswap-agent 1.3.0
* Set Monday as first day of week for French locale
* Improve date formatting in calendar view with locale fr
* Check for permission when downloading files
* Validate decimal's scale and precision attributes

## 5.1.0-rc1 (22-04-2019)

#### New Features

* New event system similar to CDI 2.0 event api
* Support for JPA event listeners
* Complete re-write of view extensions

#### Enhancements

* Major refactoring in auth api
* Improved xml view handling
* Prevent initial data fetch in grid view with `x-no-fetch="true"`
* Support for custom action on chart dashlet with `onAction` attribute
* Improved user preferences view
* Add support for forcing user to change password
* Logout user if password is changed
* Add ability to specify free search fields
* Improve DMS loading performance
* Add menubar to cards view
* Support for sharable DMS file URLs
* Delete attachments when record is deleted
* Check for DMS permissions when making zip
* Give and remove DMS permissions recursively
* Add image and pdf preview into DMS file form
* Only generate menu for custom models if menu title is provided
* Support for settings form width for custom model
* Support for setting column sequence for custom model
* Support for 'orderBy' on custom models
* Support for label fields in custom model
* Support for field permission on custom model and custom fields
* Support for font-awesome icons in image-select widget

#### Bugs

* Fix tag-select widget issue with custom fields
* Fix @RequestScoped services in unit tests
* Fix date search in grid view when user is in different time zone
* Fix I18n message bundle cache causing wrong bundle update
* Fix DMS file rename issue
* Fix @RequestScoped services when used with quartz scheduler

## 5.0.14 (2019-04-17)

#### Enhancements

* Add support for changing selection-in attribute with action-attrs
* Hide attachment icon when we can't attach a file

#### Bugs

* Fix panel-tabs visibility issue
* Fix auto fill parent field on new record on gantt view
* Fix track message textbox not clearing on new record
* Fix Column filters not applied with advance search
* Fix unexpected dirty record warning when navigating form records
* Fix popover width
* Fix o2m permission issues
* Fix onLoad issue caused by json fields
* Fix empty value in html widget with firefox
* Fix dotted field not loading
* Fix translated text gets escaped

## 5.0.13 (2019-03-22)

#### Enhancements

* Use fixed width columns in kanban view
* Add support to hide kanban columns using view-param
* Add support for multiline widget attribute to custom fields

#### Bugs

* Fix editable grid updating wrong record
* Fix i18n message extractor updating catelogs with wrong translations
* Fix required field in editor causing infinite fetch requests
* Fix NPE when using quartz job context
* Fix @RequestScoped services in unit tests
* Fix @RequestScoped services when used with quartz scheduler
* Fix data export issue from panel-dashlet
* Fix menu search
* Fix advance search issue with contains/not contains filter

## 5.0.12 (2019-01-31)

#### Enhancements

* Show exception message in prod mode (but no stacktrace)
* Add support for summary popup on kanban cards
* Add support for preventing initial data fetch in grid view (x-no-fetch="false")
* Remove kanban view restriction of max 6 columns

#### Bugs

* Fix DMS file rename issue when file name contains single quote
* Fix RefSelect sometime doesn't use configured views
* Fix kanban view scrolling
* Fix xml view validation issue

## 5.0.11 (2019-01-15)

#### Bugs

* Fix panel-tabs visibility issue
* Fix selected row flag reset issue
* Fix pagination issue in dms view

## 5.0.10 (2018-12-21)

#### Bugs

* Fix typo in Query#fetchStream methods
* Fix boolean-radio widget issue
* Check for parent to determine if a widget is hidden (#33)
* Fix I18nBundle initialization issues
* Fix idle in transaction when using quartz
* Fix pending data import of action menus not resolved 

## 5.0.9 (2018-11-28)

#### Bugs

* Fix record copy api
* Fix `requiredIf` is not applied if used with `showIf`
* Fix filter input focus issue in grid dashlet
* Fix required field clear issue in editable grid
* Fix dialog overlay opacity
* Fix column sizing issue in popup

#### Enhancements

* Allow duplicating unsaved row in o2m/m2m
* Support for `sortable` attribute on grid view
* Support for `sortable` attribute on grid view fields

## 5.0.8 (2018-11-06)

#### Bugs

* Fix view popup is now opening if first view is grid
* Fix technical info popup doesn't show value of m2o fields on o2m editor
* Fix grid column size issue in popup
* Fix server error dialog from popup is not visible
* Fix grid widget row selection issue
* Fix context update issue with panel-dashlet
* Fix invalid session error on system info page
* Fix MS-Edge issue
* Fix integer value formatting in track messages
* Fix grouped grid alignment issue in modern theme
* Fix m2o field validation issue
* Fix dirty record issue on copy
* Fix number widget increment issue
* Fix button focus style issues
* Fix calendar view doesn't use predefined filters
* Fix page size input/button alignment
* Fix translation extract issue
* Fix default values on custom models
* Fix default values in custom forms
* Fix unnecessary scrollbar in mailbox view
* Fix filter input focus issue in grid dashlet

#### Enhancements

* Allow to open popup in maximized state (use `popup.maximized` view param)
* Bring back `view.confirm.yes-no` config
* Add MultiSelect widget support in grid view
* Refresh kanban view when moving card fails
* Use special user form view from message link (user-info-form)
* Improve upload progress popup
* Improve sidebar menu UX

## 5.0.7 (2018-10-05)

#### Bugs

* Fix regression caused by RM-13705

## 5.0.6 (2018-09-14)

#### Bugs

* Fix ZonedDateTime adapter
* Fix encrypted field migration
* Fix dummy values from field editor missing in context
* Fix dummy fields issues in relational field editor
* Fix homeAction field in User m2o editor
* Fix full send in message details
* Fix dotted fields in field editor causing form dirty
* Fix onNew with save action
* Fix sidebar style conflict issue with field editor
* Fix contextual custom field with hidden=true
* Fix xml view hot reload
* Fix onSave/onLoad actions on custom models
* Fix view toolbar visibility on side change
* Fix translation popup for multiline text fields
* Fix unnecessary fetch request for dummy fields
* Fix scroll position issue on grid view when switching views

#### Enhancements

* Order followers with their namecolum
* Open the Inbox mail view instead of the unread mail view
* Prevent sorting on dummy fields
* Improve modern theme
* Terminate pending actions if view is switched
* Reset form view when switched over with browser back action
* Do not load record in form view if view is switched
* Do not add grid/form views when opening view with action

#### Others

* Adopt new style guide (google java format, two spaces for indentation)

## 5.0.5 (2018-08-03)

#### Bugs

* Fix advance search popup not hiding on navbar click
* Fix issue with custom filter sharing
* Fix translations
* Fix selection widget issue when value has html escape values
* Fix validation issue on date widget
* Fix form layout issue
* Fix extra scrollbar with html view
* Fix route change issue with html view
* Fix tab refresh issue on tree view
* Fix class path scanner issue with duplicate classes from bootstrap loader

#### Enhancements

* Add support for domain filter blacklist pattern

  ```
  domain.blacklist.pattern = (\\(\\s*SELECT\\s+)|some_function
  ```

  The old `domain.allow.sub-select` settings is removed in favor of this one.

## 5.0.4 (2018-07-10)

#### Bugs

* Fix form layout regressions
* Revert fix for conditional expressions on fields on editable grid

## 5.0.3 (2018-07-09)

#### Enhancements

* Ref-select widget should not allow editing record
* Panel header is now clickable if canCollapse is true
* Improve form layout
* Improve modern theme
* Improve kanban design/UX
* Add encryption support on large text fields
* Bring back LDAP and CAS integration
* Change `X-References` to `References` header in email message

#### Bugs

* Fix selection popup record ordering issue
* Fix conditional expressions on fields on editable grid
* Fix requiredIf condition issue
* Fix group maping from LDAP issue
* Fix advance search input issue
* Fix JavaEL expression issue
* Fix grid widget grouping issue on hidden column
* Fix NPE when trying to delete non-existent record
* Fix all day event issue in calendar view
* Fix resource leak when generating report pdf

## 5.0.2 (2018-06-20)

#### Enhancements

* Add support to disallow sub-select in domain filters with `domain.allow.sub-select = false`

#### Bugs

* Fix tag select widget issue on firefox
* Fix checkbox field in editable grid

## 5.0.1 (2018-06-18)

#### Bugs

* Fix editable grid cell focus issue
* Fix editable o2m item remove issue
* Fix widget attribute reset issue
* Fix kanban view missing values issue after card move
* Fix bulk update/delete issue with MySQL
* Fix conditional permissions with empty params value
* Fix conditional permissions not checked against database values

## 5.0.0 (2018-06-11)

#### New Features

* Encrypted field support

#### Enhancements

* Simplified access control rules
* Improve boolean widget readonly style
* Improve nav-select and boolean widget readonly style
* Remove unique constraints from User's name and email fields
* Add message stream widget to teams form
* Clear search value from advance filter when chaning field

#### Bugs

* Fix print.css
* Fix missing help icon on some widgets
* Fix groovy support
* Fix json fields validation issue
* Fix calendar view not fetching all events
* Fix kanban drag and drop issue on firefox
* Fix advance filter save issue with dotted fields
* Fix value enum log message
* Fix long command line issue on windows
* Fix context filter ignored when exporting data
* Fix file upload whitelist not checked with file fields
* Fix grid widget auto size issue
* Fix unable translate field value from unsaved records
* Fix validation error notification not shown from popup
* Fix search text validation issue on m2o field

## 5.0.0-rc5 (2018-05-14)

#### New Features

* Allow to export with single click
* Disable full export with `view.adv-search.export.full = false`
* Add support for file type whitelist & blacklist for upload

#### Bugs

* Fix non-imported incoming emails marked as seen issue
* Fix stream message mail subject issue
* Fix file attachment issue for stream message from popup composer
* Fix concurrent mail fetching issue of stream replies
* Fix extension view is include multiple time issue
* Fix route change issue from kanbank view
* Fix `file.upload.size` setting was not used whith DMS interface
* Refresh cards view after deleting a card to fix pagination issue
* Fix regression caused by search text validation on m2o

## 5.0.0-rc4 (2018-05-08)

#### Enhancements

* Updated translations

#### Security Fixes

* Fix file upload issue where file can be saved outside upload directory

#### Bugs

* Close mail inbox after fetching messages
* Fix tree view field mapping
* Fix group permission issue on menus
* Fix grid column alignment issue in popup
* Fix onNew event issue on popup editor
* Fix image widget regression
* Revert "Current user password should be required for changing users"

## 5.0.0-rc3 (2018-05-03)

#### Enhancements

* Prevent form view to switch when actions are in progress
* Improved maven publishing
* Use title as on grid button tooltip if help is not given
* Allow viewer template on relational fields without editor
* Improved new modern theme

#### Security Enhancements

* Add `session.cookie.secure` config option, can be used when app is served over https
* Do not show error stacktrace in production mode to prevent leaking sensitive details
* Current user password is required for creating or changing user records
* Reset session id to prevent session fixation

#### Security Fixes

* Fix user password hash in response
* Fix XSS vulnerability by sanitizing text values

#### Bugs

* Fix kanban column no records message dispay issue
* Fix $number helper is missing for viewer templates
* Fix code generation with gradle composite builds
* Fix m2o column reset issue with onNew action
* Fix dotted field issue in saved search filter
* Fix record delete issue with form only action-view
* Fix m2o validation issue when clearing search text

## 5.0.0-rc2 (2018-04-13)

#### New Features

* Add maven publish support in gradle plugin
* Added new clean theme "Modern"
* New setting `view.tabs.max` to control maximum number of tabs
* Set calendar date with `calendarDate` from action context
* Set calendar mode with `calendarMode` from action context
* Currency formatting using `x-currency="field.name"`
* Allow to provide custom css using `__config__.appStyle`
* Filter detail of grid view is now accessible from action context 

#### Enhancements

* Added adblocker detection (warns on login page if adblocker is detected)
* Added browser detection (warns on login page is using IE <= 11)
* Allow any action as home action if home attribute is true
* Optimize tooltip initialization
* Improve UI layout for better theme support
* Allow to show html view in popup
* Allow to manage view menus and menu items with field perms
* Only show technical popup to technical staff even in dev mode
* Optimize DMS view with huge file structure
* Support `customSearch` and `freeSearch` attributes to cards and kanban views
* Data export can be controlled with `data.export.max-size` and `data.export.fetch-size`
* Optimize grid widget rendering
* Optimize web ui by reducing DOM size
* Customize menu for custom models
* Thousand separator formatting for numeric fields
* Implemented versioned bulk update
* Custom fields in grid view are now added before buttons

#### Bugs

* Fix calendar view not using grid view filter
* Fix module uninstall issue
* Fix module install issue
* Fix selected row color issue in grid view when row is highlighted
* Fix encoding for CSV files
* Fix xml import eval attribute not supporting call actions
* Fix grid widget auto size issue with grouped data
* Fix XML source file processing on Windows
* Fix html widget style issues
* Fix issues with editable grid when all fields of the row are readonly
* Fix o2m/m2m field dropdown was not visible in editable grid
* Fix editable grid was not marking parent form dirty
* Fix mass update issues with null
* Fix navigation tabs icon and colors not updated properly
* Fix grid view reload with button action
* Fix placeholder issue on editor fields
* Fix `_model` key missing in context
* Fix translate icon on field without label
* Fix reference column formatting in tree view
* Fix view xsd having action-view attribute home in wrong place
* Fix o2m/m2m fields should always show archived records
* Fix m2o selection should not include archived records
* Fix duplicate row created on o2m when an action is using `response.setValues`
* Fix attachment file updates with DMS view
* Fix NPE caused by mail fetcher job
* Fix `freeSearch` with name field not working on grid view
* Fix various popup dialog layout issues
* Fix advance search not visible in view popup
* Fix memory leaks in web ui
* Fix parent reload from popup 
* Fix unarchive menu item not visible in form view
* Fix dotted fields in editable grid not updated if related m2o changes 
* Fix popup editor readonly issue
* Fix o2m editable grid sometime duplicates previous cell's value when creating new rows
* Fix time widget update issue in editable grid view
* Fix m2o field dropdown menu in editable grid
* Fix mass updatable field sometime not listed
* Fix menu overriding issue caused by wrong ordering
* Fix xml id is not utilized for menu and action definitions
* Fix context update issue caused by `response.setValues` call
* Fix value formatting issues in tree view
* Fix `nav-select` widget initialization issue
* Fix advance search field selection sorting
* Fix view tabs icon and colors not updated properly
* Fix translatable field value is sometime not translated

## 5.0.0-rc1 (2018-02-07)

#### New Features

* Migrate to Java8
* Migrate to Hibernate 5
* Migrate to java.time (drop joda.time)
* Use HikariCP as connection pool
* Oracle database support (12c)
* MySQL database support (5.7)
* Multi-Tenancy support
* Improved logging with logback
* Tomcat 8.5 and Servlet API 3.1
* Full-text search support using hibernate-search
* Sidebar menu search
* CSV export from dashlet/o2m/m2m
* Dynamic custom fields support
* Dynamic custom models support
* Contextual advance search for custom fields
* Context aware grid columns for custom fields
* Automatic form & grid views for custom models
* Master-Details support on main grid view
* Basic teams/tasks features
* JCache integration for hibernate L2-cache
* JavaScript scripting support using Nashorn
* Add new action-script action
* Add hot code change support using hotswap-agent (experimental)
* Add hot view xml changes (experimental)
* Add Intellij IDE support
* Improved Eclipse IDE support using buildship
* New embedded tomcat runner with hotswap and debugging support
* Add support to define help for views externally and overrides help defined in views
* Add SLF4J logger injection support
* Add enum type fields support
* Kotlin and Scala support

#### Enhancements

* Support for `join-table` on m2m fields
* Color support in stdout logging 
* Allow to override file upload directory structure
* Optimized code generation gradle task
* Allow to add message content with change tracking
* Re-implementation of context using proxy with seamless access to context values as well as database values
* Improve DMS ergonomics
* Allow to unarchive records
* Allow closing tabs with mouse middle click
* Re-implemented value translation feature
* Allow enhance base `Model` class with simple fields

#### Deprecations

* jdbc style positional parameters are deprecated, use JPA style positional parameters only

#### Breaking Changes

* Removed shell
* Mail groups are replaced with team (see basic teams feature)
* Method `Context#asType(Class)` returns proxy instance
* Changed scripting helper `__repo__.of()` to `__repo__()`
* Gradle tasks `init` and `migrate` are replaced with new `database` task

#### Breaking Schema Changes (from v4)

* `auth_permission.condition_value` column size changed from `255` to `1024`
* `mail_group` table dropped
* `mail_group_users` table dropped
* `mail_group_groups` table dropped
* `meta_module.depends` column dropped
* `meta_translation.message_key` column type changed from `text` to `varchar(1024)`
* `meta_translation.message_value` column type changed from `text` to `varchar(1024)`
