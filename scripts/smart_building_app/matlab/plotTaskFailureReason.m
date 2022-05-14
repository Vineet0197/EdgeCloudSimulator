function [] = plotTaskFailureReason()

    plotGenericResult(1, 10, 'Failed Task due to VM Capacity (%)', 'ALL_APPS', 'percentage_for_failed');
    plotGenericResult(1, 10, {'Failed Task due to VM Capacity';'for SMART Building Application (%)'}, 'SMART_BUILDING', 'percentage_for_failed');

    plotGenericResult(1, 11, 'Failed Task due to Mobility (%)', 'ALL_APPS', 'percentage_for_failed');
    plotGenericResult(1, 11, {'Failed Task due to Mobility';'for SMART Building Application (%)'}, 'SMART_BUILDING', 'percentage_for_failed');

    plotGenericResult(1, 4, 'Failed Tasks due to Network failure (%)', 'ALL_APPS', 'percentage_for_failed');
    plotGenericResult(1, 4, {'Failed Tasks due to Network failure';'for SMART Building Application (%)'}, 'SMART_BUILDING', 'percentage_for_failed');
end